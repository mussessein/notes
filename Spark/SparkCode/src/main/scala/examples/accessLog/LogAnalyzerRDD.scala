package examples.accessLog

import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 理想数据的统计模板：不存在脏数据
 * 存在脏数据，会无法解析数据
 * 在读取文件之后，filter算子进行过滤
 *
 *
 * 日志分析准备：
 * (1). 读取数据
 * (2). filter过滤脏数据
 * (3). 将每条数据封装为 对象
 * 日志分析需求：
 * (1).统计最后一个字段contentSize的：avg，min，max
 * (2).统计responseCode字段：统计各个响应码的次数
 * (3).IP字段：统计每个ip的访问次数
 * (4).endPoint：统计各个接口的访问次数
 */
object LogAnalyzerRDD {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("TrackLog Analyzer Application")
    val sc = SparkContext.getOrCreate(sparkConf)

    /**
     * 读取数据，过滤脏数据，封装对象
     */
    val logFile = "input/access_log"
    val accessLogRDD: RDD[String] = sc
      .textFile(logFile)
      // 过滤脏数据
      .filter(log => AccessLog.isValidateLogLine(log))

    // log:1.1.1.1 - - [21/Jul/2014:10:00:00 -0800] "GET /chapter1/java/src/main/java/com/databricks/apps/logs/LogAnalyzer.java HTTP/1.1" 200 1234

    /**
     * 解析数据：（数据的每个字段都是什么，一般用一个case class封装）
     * 在case class中声明一个静态解析方法
     * 将每行数据封装为一个对象
     */
    val parseRDD: RDD[AccessLog] = accessLogRDD
      .map(log => AccessLog.parseLogLine(log))
    //AccessLog(1.1.1.1,-,-,21/Jul/2014:10:00:00 -0800,GET,/chapter1/java/src/main/java/com/databricks/apps/logs/LogAnalyzer.java,HTTP/1.1,200,1234)
    parseRDD.persist(StorageLevel.MEMORY_ONLY)

    /**
     * 需求一
     * 1. 拿到每行的最后一个字段：_.contentSizeRDD ===> cache一下，后面要用多次
     * 2. 求平均
     * 3. min
     * 4. max
     */
    println("====================需求一====================")
    val contentSizeRDD: RDD[Long] = parseRDD.map(_.contentSize)
    // average
    val avgContentSize: Long = contentSizeRDD.reduce(_ + _) / contentSizeRDD.count()
    // min
    val maxContentSize: Long = contentSizeRDD.max()
    // max
    val minContentSize: Long = contentSizeRDD.min()
    // 释放内存
    println(s"Avg:${avgContentSize},Min:${minContentSize},Max:${maxContentSize}")

    /**
     * 需求二
     * 1. 拿到responseCode字段，变成tuple(，1)
     */
    println("====================需求二====================")
    val responseRDD: Array[(Int, Int)] = parseRDD
      .map(log => (log.responseCode, 1))
      .reduceByKey(_ + _)
      .collect()
    println(s"responseCode count:${responseRDD.mkString("[", ",", "]")}")


    /**
     * 需求三
     * 1. 拿到responseCode字段，变成tuple(，1)
     */
    println("====================需求三====================")
    val ipAddressRDD: Array[(String, Int)] = parseRDD
      .map(log => (log.ipAddress, 1))
      .reduceByKey(_ + _).take(20)
    println(s"ipAddressRDD count:${ipAddressRDD.mkString("[", ",", "]")}")

    /**
     * 需求四
     * 1. endPoint：统计各个接口的访问次数，通过次数，降序排序
     */
    println("====================需求四====================")
    val endPointRDD: Array[(String, Int)] = parseRDD
      .map(log => (log.endPoint, 1))
      .reduceByKey(_ + _)
      .map(tuple => (tuple._2, tuple._1))
      .sortByKey(ascending = false) // 降序
      .take(5)
      .map(tuple => (tuple._2, tuple._1))
    println(s"endPointRDD count:${endPointRDD.mkString("[", ",", "]")}")

    //释放内存
    parseRDD.unpersist()
    sc.stop()
  }
}
