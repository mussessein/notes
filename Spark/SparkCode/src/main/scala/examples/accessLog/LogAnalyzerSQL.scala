package examples.accessLog

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.storage.StorageLevel

/**
 *
 * sql处理日志分析准备：
 * (1). 读取数据
 * (2). filter过滤脏数据
 * (3). 将每条数据封装为 对象
 * sql处理日志分析需求：
 * (1).统计最后一个字段contentSize的：avg，min，max
 * (2).统计responseCode字段：统计各个响应码的次数
 * (3).IP字段：统计每个ip的访问次数
 * (4).endPoint：统计各个接口的访问次数
 */
object LogAnalyzerSQL {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql log analysis")
      .master("local[*]")
      .getOrCreate()
    // 通过sparkSession拿到spark上下文对象
    val sc: SparkContext = spark.sparkContext
    // 为了使得RDDS=>DataFrames
    // 自定义一个udf函数
    spark.udf.register(
      "toLower",
      (word: String) => word.toLowerCase()
    )

    /**
     * 读取数据，过滤脏数据，封装对象===>转换为DataFrame
     */
    val logFile = "input/access_log"
    val parseRDD: RDD[AccessLog] = sc
      .textFile(logFile)
      // 过滤脏数据
      .filter(log => AccessLog.isValidateLogLine(log))
      .map(log => AccessLog.parseLogLine(log))
    // 转DF
    import spark.implicits._
    val accessLogsDF: DataFrame = parseRDD.toDF()
    // 创建临时视图
    accessLogsDF.createOrReplaceTempView("tmp_access_log")
    // 持久化
    accessLogsDF.persist(StorageLevel.MEMORY_ONLY)
    // 数据总条数
    spark.sql(
      """
        |SELECT
        |COUNT(*)
        |AS cnt
        |FROM
        |tmp_access_log
        |""".stripMargin).show()

    /**
     * 需求一
     * 1. 拿到每行的最后一个字段：_.contentSizeRDD ===> cache一下，后面要用多次
     * 2. 求平均
     * 3. min
     * 4. max
     */
    println("====================需求一====================")
    // row就是一行记录的集合.下面的查询语句只会返回一条数据，直接first()
    val contentSizeRow: Row = spark.sql(
      """
        |SELECT
        |SUM(contentSize),COUNT(*),MIN(contentSize),MAX(contentSize)
        |FROM
        |tmp_access_log
        |""".stripMargin).first()
    println(
      s"Avg:${contentSizeRow.getLong(0) / contentSizeRow.getLong(1)}" +
        s",Min:${contentSizeRow.getLong(2)}" +
        s",Max:${contentSizeRow.getLong(3)}")

    /**
     * 需求二
     * 1. 拿到responseCode字段，统计各个响应码的次数
     */
    println("====================需求二====================")
    // 下面查询会返回多行数据,通过map对每行数据进行处理
    val responseRDD: Array[(Int, Long)] = spark.sql(
      """
        |SELECT
        |responseCode,COUNT(responseCode) AS cnt
        |FROM
        |tmp_access_log
        |GROUP BY
        |responseCode
        |""".stripMargin).map(row => (row.getInt(0), row.getLong(1))).collect()
    println(s"responseCode count:${responseRDD.mkString("[", ",", "]")}")

    /**
     * 需求三
     * 1. IP字段：统计每个ip的访问次数
     */
    println("====================需求三====================")
    val ipAddressRDD: Array[(String, Long)] = spark.sql(
      """
        |SELECT
        |ipAddress,COUNT(*) AS cnt
        |FROM
        |tmp_access_log
        |GROUP BY
        |ipAddress
        |HAVING
        |cnt > 30
        |LIMIT 10
        |""".stripMargin).map(row => (row.getString(0), row.getLong(1))).collect()
    println(s"ipAddressRDD count:${ipAddressRDD.mkString("[", ",", "]")}")

    /**
     * 需求四
     * 1. endPoint：统计各个接口的访问次数，通过次数，降序排序
     */
    println("====================需求四====================")
    val endPointRDD: Array[(String, Long)] = spark.sql(
      """
        |SELECT
        |endPoint,COUNT(*) AS cnt
        |FROM
        | tmp_access_log
        |GROUP BY
        | endPoint
        |ORDER BY
        | cnt DESC
        |LIMIT 5
        |""".stripMargin).map(row => (row.getString(0), row.getLong(1))).collect()
    println(s"endPointRDD count:${endPointRDD.mkString("[", ",", "]")}")

    accessLogsDF.unpersist()
    Thread.sleep(100000000000L)
    sc.stop()
  }
}
