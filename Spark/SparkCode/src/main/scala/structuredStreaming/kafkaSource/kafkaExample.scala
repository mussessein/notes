package structuredStreaming.kafkaSource

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * structured-streaming-kafka-2.3.1官方文档：
 * https://spark.apache.org/docs/2.3.1/structured-streaming-kafka-integration.html
 * 依赖：
 * groupId = org.apache.spark
 * artifactId = spark-sql-kafka-0-10_2.11
 * version = 2.3.1
 */
object kafkaExample {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("structured streaming kafka")
      .master("local[2]")
      .getOrCreate()
    /**
     * kafka配置：
     * 1. format("kafka")定义source为kafka
     * 2. option("kafka.bootstrap.servers", "host1:port1,host2:port2")集群
     * 3. option("subscribe", "topic1,topic2")：订阅主题，可以多个主题，支持pattern topic*
     * 4. 其他更多配置也是通过option来定义
     */
    val df: DataFrame = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
      .option("subscribe", "topic1,topic2") // 这里可以订阅多个topic
      .load()

    /**
     * 设置kafka的key，value都为 String
     */
    import spark.implicits._
    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
  }
}
