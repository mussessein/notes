package examples.project

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Result
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 使用SparkCore从HBase表中读取数据
 */
object ReadHBaseTableDataSpark {

  /**
   * DriverProgram
   */
  def main(args: Array[String]): Unit = {

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


    /**
     * def newAPIHadoopRDD[K, V, F <: NewInputFormat[K, V]](
     * conf: Configuration = hadoopConfiguration,
     * fClass: Class[F],
     * kClass: Class[K],T
     * vClass: Class[V]): RDD[(K, V)]
     */
    // 1. 读取配置信息
    val conf: Configuration = HBaseConfiguration.create()
    // 设置从哪个表中读取数据
    conf.set(TableInputFormat.INPUT_TABLE, "ht_wordcount")

    // 2. 调用newAPIHadoopRDD获取数据，底层MapReduce读取HBase表的程序
    val resultRDD: RDD[(ImmutableBytesWritable, Result)] = sc.newAPIHadoopRDD(
      conf,
      classOf[org.apache.hadoop.hbase.mapreduce.TableInputFormat],
      classOf[ImmutableBytesWritable],
      classOf[Result]
    )

    // 3. 测试获取的数据
    println("Count = " + resultRDD.count)

    // 打印数据
    resultRDD.take(5).foreach {
      case (key, result) => {
        // 获取RoeKey
        val rowkey = Bytes.toString(key.get())
        println(s"RowKey = ${rowkey}")
        //
        for (cell <- result.rawCells()) {
          val cf = Bytes.toString(CellUtil.cloneFamily(cell))
          val column = Bytes.toString(CellUtil.cloneQualifier(cell))
          val value = Bytes.toString(CellUtil.cloneValue(cell))
          //
          println(s"\t ${cf}:${column} = ${value}")
        }
      }
    }

    // 开发测试的时候，为了监控Spark Application状况
    Thread.sleep(10000000)

    // 3. 关闭SparkContext
    sc.stop()
  }

}
