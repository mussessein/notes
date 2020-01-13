package sparkStreaming.SSKafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

/**
 * Spark Streaming kafka的老版 API
 * http://spark.apache.org/docs/latest/streaming-kafka-0-10-integration.html
 */
object KafkaExample {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 配置kafka信息
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "hadoop001:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "auto.offset.reset" -> "latest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
    // 订阅的topic
    val topics: Array[String] = Array("spark")

    // 读数据
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    // 处理数据
    val wordsStream: DStream[String] = stream.map(record => record.value)
    val result: DStream[(String, Int)] = wordsStream.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

    result.print()

    ssc.start()
    // Wait for the execution to stop
    ssc.awaitTermination()

    // 关闭
    ssc.stop(true, true)
  }
}
