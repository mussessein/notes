package examples.project.etl

import java.util
import java.util.zip.CRC32

import com.hpsk.bigdata.spark.project.common.EventLogConstants
import com.hpsk.bigdata.spark.project.common.EventLogConstants.EventEnum
import com.hpsk.bigdata.spark.project.util.{LogParser, TimeUtil}
import examples.project.common.EventLogConstants
import org.apache.commons.lang.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client.{HBaseAdmin, HTable, Put}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


/**
 * 基于Spark框架读取HDFS上日志文件数据，进行ETL操作，最终将数据插入到HBase表中
 */
object EtlToHBaseSpark {

  /**
   * 依据服务器时间 + 用户ID + 会员ID + 事件名称 产生RowKey
   *
   * @param time
   * 访问服务器的时间
   * @param u_ud
   * uuid, 用户ID，唯一标识用户访客
   * @param u_md
   * memberid, 会员ID， 业务系统的用户ID
   * @param event_alias
   * 事件类型的别称
   * @return
   */
  def createRowKey(time: Long, u_ud: String, u_md: String,
                   event_alias: String): String = {
    // 创建CRC32实例对戏，进行字符串编码，将字符串转换为Long类型的数字
    val crc32 = new CRC32()
    // 创建StringBuilder对象
    val sbuilder = new StringBuilder()
    sbuilder.append(time + "_")

    crc32.reset()
    if (StringUtils.isNotBlank(u_ud)) {
      crc32.update(Bytes.toBytes(u_ud))
    }
    if (StringUtils.isNotBlank(u_md)) {
      crc32.update(Bytes.toBytes(u_md))
    }
    if (StringUtils.isNotBlank(event_alias)) {
      crc32.update(Bytes.toBytes(event_alias))
    }

    sbuilder.append(crc32.getValue() % 100000000L)

    // 返回RowKey
    sbuilder.toString()
  }

  /**
   * 创建HBase表，如果表存在，先删除，后创建
   *
   * @param processDate
   * 要处理哪天的数据的日期，格式 2017-07-24
   * @param conf
   * HBase Client要读取的配置信息
   * @return
   */
  def createHBaseTable(processDate: String, conf: Configuration): String = {
    // HBase中表的名称 按照 一天一张表 设计的，如何event_logs20170724
    val time = TimeUtil.parseString2Long(processDate)
    val date = TimeUtil.parseLong2String(time, "yyyyMMdd")
    // tableName
    val tableName = EventLogConstants.HBASE_NAME_EVENT_LOGS + date

    // 创建表
    var admin: HBaseAdmin = null
    // 捕获异常
    try {
      //
      admin = new HBaseAdmin(conf)
      // 判断表是否存在
      if (admin.tableExists(tableName)) {
        // 如果存在，进行删除
        admin.disableTable(tableName)
        admin.deleteTable(tableName)
      }

      // HTable
      val desc = new HTableDescriptor(TableName.valueOf(tableName))
      // 向表添加列簇
      desc.addFamily(
        new HColumnDescriptor(EventLogConstants.BYTES_EVENT_LOGS_FAMILY_NAME)
      )
      // 创建表
      admin.createTable(desc)

      // 返回表的名称
      tableName
    } catch {
      case e: Exception => e.printStackTrace()
        tableName
    } finally {
      if (null != admin) admin.close()
    }
  }

  /**
   * DriverProgram
   */
  def main(args: Array[String]): Unit = {

    if (args.length < 1) {
      println("Usage: EtlToHBaseSpark process_date")
      System.exit(1)
    }

    /**
     * 创建SparkContext
     */
    // 1. 创建SparkConf,设置应用配置信息
    val sparkConf = new SparkConf()
      // 设置应用的名称，显示在UI上
      .setAppName("SparkModule Application")
      // 设置应用运行的模式，开发的时候设置为local[2]本地模式，如果是集群的话，需要修改，但是通常使用命令设置
      .setMaster("local[2]")
    // 2. 创建SparkContext上下文对象，用于读取数据和调度Jobs
    // val sc = new SparkContext(sparkConf)
    val sc = SparkContext.getOrCreate(sparkConf)

    /** ================================================================================== */
    // 1. 读取日志数据，此处开发测试从HDFS上读取
    /**
     * 当SparkCore处理的数据集比较大，几十GB的话，读取HDFS上的数据，RDD的分区数 等于 block数目
     */
    val eventLogsRDD = sc.textFile("/datas/20151220.log", 3)

    // 2. 首先将数据进行过滤清洗和格式的转换
    val parseEventLogsRDD: RDD[(String, util.Map[String, String])] = eventLogsRDD
      // 解析每条日志数据
      .map(line => {
        // 調用工具类进行解析，得到Map集合
        val logInfo: util.Map[String, String] = new LogParser().handleLogParser(line)
        // 获取事件类型
        val event_alias: String = logInfo.get(EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME)
        // 返回
        (event_alias, logInfo)
      })

    // 定义广播变量，存储事件类型数据，在程序执行的时候，广播出去
    val eventTypeList = List(EventEnum.LAUNCH, EventEnum.PAGEVIEW, EventEnum.CHARGEREQUEST,
      EventEnum.CHARGESUCCESS, EventEnum.CHARGEREFUND, EventEnum.EVENT)
    val eventTypeBroadcast: Broadcast[List[EventEnum]] = sc.broadcast(eventTypeList)

    val eventPutRDD = parseEventLogsRDD
      // 对事件Event类型进行过滤
      .filter(tuple => eventTypeBroadcast.value.contains(EventEnum.valueOfAlias(tuple._1)))
      // 数据转换，转换为向HBase表插入数据所要求的RDD的格式, RDD[(ImmutableBytesWritable, Put)]
      .map {
        case (event_alias, logInfo) => {
          // 生成HBase表中的RowKey
          val rowKey = createRowKey(
            // 访问服务器时间
            TimeUtil.parseNginxServerTime2Long(
              logInfo.get(EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME)
            ),
            // 用户ID
            logInfo.get(EventLogConstants.LOG_COLUMN_NAME_UUID),
            // 会员ID
            logInfo.get(EventLogConstants.LOG_COLUMN_NAME_MEMBER_ID),
            // 事件类型的别名
            event_alias
          )
          // 创建Put对象
          val put = new Put(Bytes.toBytes(rowKey))
          // add columns
          // 此处要注意，由于logInfo为Java中的Map，如果要循环遍历，需要Java中的Map转换为Scala中Map
          import scala.collection.JavaConverters._
          for ((key, value) <- logInfo.asScala.toMap) {
            put.add(
              EventLogConstants.BYTES_EVENT_LOGS_FAMILY_NAME, // cf
              Bytes.toBytes(key), // column
              Bytes.toBytes(value)
            )
          }

          // 返回put对象
          (new ImmutableBytesWritable(Bytes.toBytes(rowKey)), put)
        }
      }

    println(s"eventPutRDD Count = ${eventPutRDD.count()}")

    /**
     * HBase 相关配置信息：
     * 表的名称， 输出格式outputFormat，输出目录output.dir
     */
    // 将hbase-site.xml,hdfs-site.xml,core-site.xml放入CLASSPATH
    val conf = HBaseConfiguration.create()
    // 设置输出的OutputFormat
    conf.set("mapreduce.job.outputformat.class",
      "org.apache.hadoop.hbase.mapreduce.TableOutputFormat")
    // 设置输出的目录
    conf.set("mapreduce.output.fileoutputformat.outputdir",
      "/datas/spark/hbase/etl-ouptut" + System.currentTimeMillis())
    /**
     * 由于ETL每天执行一次，对原始数据进行处理，将每天的数据存储在HBase的一张表中
     * 表明 eventlog + date
     * 自己判断表是否存在，不存在创建
     */
    val tableName = createHBaseTable(args(0), conf)
    // 设置要输出到HBase表的名称
    conf.set(TableOutputFormat.OUTPUT_TABLE, tableName)

    // 3. 将RDD插入到HBase表中
    eventPutRDD.saveAsNewAPIHadoopDataset(conf)

    /** ================================================================================== */
    // 开发测试的时候，为了监控Spark Application状况
    Thread.sleep(10000000)

    // 3. 关闭SparkContext
    sc.stop()
  }

}
