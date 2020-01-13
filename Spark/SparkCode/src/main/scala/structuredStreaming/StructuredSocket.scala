package structuredStreaming

import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * Structured Streaming的 WordCount例子
 * $ nc -lk 9999
 */
object StructuredSocket {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .appName("StructuredNetworkWordCount")
      .getOrCreate()

    import spark.implicits._
    // 创建连接 socket，读取端口信息
    val lines: DataFrame = spark
      .readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()
    // Split the lines into words
    val words: Dataset[String] = lines.as[String].flatMap(_.split(" "))

    // Generate running word count("value"为视图的一个列)
    val wordCounts: DataFrame = words.groupBy("value").count()
    // Start running the query that prints the running counts to the console
    // 开启流计算，并打印全部结果到控制台
    val query: StreamingQuery = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    // 退出计算（不会中断计算）
    query.awaitTermination()

    //    # TERMINAL 1:
    //    # Running Netcat
    //
    //    $ nc -lk 9999
    //      apache spark
    //      apache hadoop

    //    $ ./bin/run-example org.apache.spark.examples.sql.streaming.StructuredNetworkWordCount localhost 9999
    //
    //    -------------------------------------------
    //    Batch: 0
    //    -------------------------------------------
    //    +------+-----+
    //    | value|count|
    //    +------+-----+
    //    |apache|    1|
    //    | spark|    1|
    //    +------+-----+
    //
    //    -------------------------------------------
    //    Batch: 1
    //    -------------------------------------------
    //    +------+-----+
    //    | value|count|
    //    +------+-----+
    //    |apache|    2|
    //    | spark|    1|
    //    |hadoop|    1|
    //    +------+-----+
  }
}
