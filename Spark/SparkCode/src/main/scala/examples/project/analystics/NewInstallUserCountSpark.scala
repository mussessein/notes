package examples.project.analystics

import java.util.Calendar

import com.hpsk.bigdata.spark.project.common.EventLogConstants
import com.hpsk.bigdata.spark.project.common.EventLogConstants.EventEnum
import com.hpsk.bigdata.spark.project.util.TimeUtil
import org.apache.commons.lang.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.{Result, Scan}
import org.apache.hadoop.hbase.filter.{CompareFilter, SingleColumnValueFilter}
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.{Base64, Bytes}
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 基于SparkCore实现 新增用户统计分析
 * -1. 需求： 新增用户是什么呢？？
 * 第一次访问就是一个新增的用户
 * 如何表示一个新增用户，触发一个事件launch事件 en=e_l
 */
object NewInstallUserCountSpark {

  /**
   * DriverProgram
   */
  def main(args: Array[String]): Unit = {

    if (args.length < 1) {
      println("Usage: NewInstallUserCountSpark process_date")
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
      // .setMaster("local[2]")
      /**
       * 设置Spark数据的序列化
       */
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      //
      .registerKryoClasses(Array(classOf[ImmutableBytesWritable], classOf[Result]))

    // 2. 创建SparkContext上下文对象，用于读取数据和调度Jobs
    // val sc = new SparkContext(sparkConf)
    val sc = SparkContext.getOrCreate(sparkConf)
    // 设置日志级别
    // sc.setLogLevel("WARN")

    /** ================================================================================== */
    // 1. 从HBase表中读取数据，依据需求进行过滤筛选
    /**
     * 在此业务分析中，只需要 事件为 launch 产生的数据
     * 所需要的字段信息：
     *       - uuid: 访客id
     *       - s_time: 访问服务器的时间
     *       - pl: platform 平台的类型
     *       - version: 平台的版本
     *       - browserName: 浏览器名称
     *       - browserVersion: 浏览器版本
     */

    // 1. 读取配置信息
    val conf: Configuration = HBaseConfiguration.create()

    // HBase中表的名称 按照 一天一张表 设计的，如何event_logs20170724
    val time = TimeUtil.parseString2Long(args(0))
    val date = TimeUtil.parseLong2String(time, "yyyyMMdd")
    // tableName
    val tableName = EventLogConstants.HBASE_NAME_EVENT_LOGS + date
    // 设置从哪个表中读取数据
    conf.set(TableInputFormat.INPUT_TABLE, tableName)

    // 设置过滤条件
    val scan = new Scan()

    // 查询某一列簇
    val FAMILY_NAME = EventLogConstants.BYTES_EVENT_LOGS_FAMILY_NAME
    scan.addFamily(FAMILY_NAME)
    // 增加 需要查询的字段
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_UUID))
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME))
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_PLATFORM))
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_VERSION))
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_BROWSER_NAME))
    scan.addColumn(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_BROWSER_VERSION))
    // 使用HBase表查询时的过滤器Filter
    scan.setFilter(
      new SingleColumnValueFilter(
        FAMILY_NAME,
        Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_EVENT_NAME),
        CompareFilter.CompareOp.EQUAL,
        Bytes.toBytes(EventEnum.LAUNCH.alias)
      )
    )

    // 设置scan 过滤， 需要将scan对象转换为String类型 -> 参考TableMapReduceUtil
    conf.set(TableInputFormat.SCAN, Base64.encodeBytes(ProtobufUtil.toScan(scan).toByteArray))

    // 2. 调用newAPIHadoopRDD获取数据，底层MapReduce读取HBase表的程序
    val eventLogsRDD: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(
      conf,
      classOf[org.apache.hadoop.hbase.mapreduce.TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    println(s"Count = ${eventLogsRDD.count()}")
    /** 测试序列化
     *eventLogsRDD.take(3).foreach{
     * case (key, result) =>{
     * // 获取RoeKey
     * val rowkey = Bytes.toString(key.get())
     * println(s"RowKey = ${rowkey}")
     * for(cell <- result.rawCells()){
     * val cf = Bytes.toString(CellUtil.cloneFamily(cell))
     * val column = Bytes.toString(CellUtil.cloneQualifier(cell))
     * val value = Bytes.toString(CellUtil.cloneValue(cell))
     * //
     * println(s"\t ${cf}:${column} = ${value}")
     * }
     * }
     * }
     */

    // 将HBase表中获取的数据进行 转换
    val newUserRDD: RDD[(String, String, String, String, String, String)] = eventLogsRDD
      .map {
        case (key, result) => {
          // 获取RoeKey
          val rowkey = Bytes.toString(key.get())

          // 获取所有字段的值
          val uuid = Bytes.toString(
            result.getValue(FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_UUID)))
          val s_time = Bytes.toString(result.getValue(
            FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_SERVER_TIME)))
          val platformName = Bytes.toString(result.getValue(
            FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_PLATFORM)))
          val platformVersion = Bytes.toString(result.getValue(
            FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_VERSION)))
          val browserName = Bytes.toString(result.getValue(
            FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_BROWSER_NAME)))
          val browserVersion = Bytes.toString(result.getValue(
            FAMILY_NAME, Bytes.toBytes(EventLogConstants.LOG_COLUMN_NAME_BROWSER_VERSION)))
          // 返回
          (uuid, s_time, platformName, platformVersion, browserName, browserVersion)
        }
      }
      // 进行过滤，当uuid 和 s_time 为null时，过滤掉
      .filter(tuple => null != tuple._1 && null != tuple._2)

    println(s"Filter Count = ${newUserRDD.count()}")

    /**
     * 新增用户的分析
     * -1. 基本维度分析
     * 时间 + 平台
     */
    val timePlatformBrowserNewUserRDD: RDD[(String, Int, String, String)] = newUserRDD
      .map {
        case (uuid, s_time, platformName, platformVersion, browserName, browserVersion) => {
          // 获取day， 一个月中第几天
          val calendar = Calendar.getInstance()
          calendar.setTimeInMillis(TimeUtil.parseNginxServerTime2Long(s_time))
          val day = calendar.get(Calendar.DAY_OF_MONTH)

          //平台维度信息
          var platformDimension: String = ""
          if (StringUtils.isBlank(platformName)) {
            platformDimension = "unknown:unknown"
          } else if (StringUtils.isBlank(platformVersion)) {
            platformDimension = platformName + ":unknown"
          } else {
            platformDimension = platformName + ":" + platformVersion
          }

          // 浏览器维度信息
          var browserDimension: String = ""
          if (StringUtils.isBlank(browserName)) {
            browserDimension = "unknown:unknown"
          } else if (StringUtils.isBlank(browserVersion)) {
            browserDimension = browserName + ":unknown"
          } else {
            browserDimension = browserName + ":" + browserVersion.split("\\.")(0)
          }

          // 最后返回
          (uuid, day, platformDimension, browserDimension)
        }
      }
      // 去重
      .distinct()

    // 由于后续分析针对上述RDD进行的，所以进行持久化
    timePlatformBrowserNewUserRDD.persist(StorageLevel.MEMORY_AND_DISK)


    /**
     * 基本维度分析
     */
    val timePlatformNewUserCountRDD: RDD[(String, Int)] = timePlatformBrowserNewUserRDD
      // 提取字段   day_platform
      .map(tuple => (tuple._2 + "-" + tuple._3, 1))
      // 聚合
      .reduceByKey(_ + _)

    // 打印
    timePlatformNewUserCountRDD.foreach(println)

    /**
     * 基本维度分析 + 浏览器维度分析
     */
    val timePlatformNewBrowserUserCountRDD: RDD[(String, Int)] = timePlatformBrowserNewUserRDD
      // 提取字段   day_platform
      .map(tuple => (tuple._2 + "-" + tuple._3 + "_" + tuple._4, 1))
      // 聚合
      .reduceByKey(_ + _)

    // 打印
    timePlatformNewBrowserUserCountRDD.foreach(println)

    //
    timePlatformBrowserNewUserRDD.unpersist()

    /** ================================================================================== */
    // 开发测试的时候，为了监控Spark Application状况
    Thread.sleep(10000000)

    // 3. 关闭SparkContext
    sc.stop()
  }

}
