package examples.project

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.io.ImmutableBytesWritable
import org.apache.hadoop.hbase.util.Bytes
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 将RDD数据保存到HBase表中
 */
object WriteDataToHBaseSpark {

  /**
   * DriverProgram
   */
  def main(args: Array[String]): Unit = {

    /**
     * 创建SparkContext
     */
    // 1. 创建SparkConf,设置应用配置信息
    val sparkConf: SparkConf = new SparkConf()
      // 设置应用的名称，显示在UI上
      .setAppName("SparkModule Application")
      // 设置应用运行的模式，开发的时候设置为local[2]本地模式，如果是集群的话，需要修改，但是通常使用命令设置
      .setMaster("local[2]")
    // 2. 创建SparkContext上下文对象，用于读取数据和调度Jobs
    // val sc = new SparkContext(sparkConf)
    val sc: SparkContext = SparkContext.getOrCreate(sparkConf)


    /**
     * 模拟数据：
     * 将词频统计 word, count 结果写入到HBase表中，其中word为Rowkey
     */
    // 创建Scala集合中List
    val list = List(("hadoop", 324), ("spark", 3000), ("hive", 1987))

    // 通过 并行化 本地集合 创建RDD
    val wordCountRDD: RDD[(String, Int)] = sc.parallelize(list)

    /**
     * HBase 表的说明：
     * 表的名称
     * ht_wordcount
     * 列簇名称
     * info
     * 列名
     * count
     * 分析：
     * HBase表汇总的数据 最终存储在 HFile文件中（StoreFile文件），放在HDFS上
     *
     * 思考：
     * 其实 Spark 将数据写入到HBase表中，底层还是 MapReduce与HBase集成，
     * MR如何将数据写入到HBase表中？？？？？
     * 关键点：
     * -1, RDD[(Key, Value)]
     * Key：NullWritable/ImmutableBytesWritable
     * Value: Put
     * -2, 设置输出格式
     *              job.setOutputFormatClass(TableOutputFormat.class);
     *              conf.set(TableOutputFormat.OUTPUT_TABLE, table);
     * -3, def saveAsNewAPIHadoopDataset(conf: Configuration): Unit
     */
    // 1. 将结果RDD转换为   RDD[(ImmutableBytesWritable, Put)]
    val putRDD: RDD[(ImmutableBytesWritable, Put)] = wordCountRDD
      // 转换数据格式
      .map(tuple => {
        // 创建Put对象
        val put = new Put(Bytes.toBytes(tuple._1))

        // add columns
        put.add(
          Bytes.toBytes("info"), // cf
          Bytes.toBytes("count"), // column
          Bytes.toBytes(tuple._2.toString)
        )

        // return
        (new ImmutableBytesWritable(Bytes.toBytes(tuple._1)), put)
      })

    // 2. 得到配置信息（涵盖HBase)
    // 将hbase-site.xml,hdfs-site.xml,core-site.xml放入CLASSPATH
    val conf: Configuration = HBaseConfiguration.create()
    // 设置要输出到HBase表的名称
    conf.set(TableOutputFormat.OUTPUT_TABLE, "ht_wordcount")
    // 设置输出的OutputFormat
    conf.set("mapreduce.job.outputformat.class",
      "org.apache.hadoop.hbase.mapreduce.TableOutputFormat")
    // 设置输出的目录
    conf.set("mapreduce.output.fileoutputformat.outputdir",
      "/datas/spark/hbase/wc-ouptut" + System.currentTimeMillis())

    // 3. 保存数据到HBase表中
    putRDD.saveAsNewAPIHadoopDataset(conf)


    // 开发测试的时候，为了监控Spark Application状况
    Thread.sleep(10000000)

    // 3. 关闭SparkContext
    sc.stop()
  }

}
