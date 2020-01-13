package sparkStreaming.SSOutput

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * saveAsTextFiles
 * 需要前缀 prefix 后缀suffix
 */
object SaveAsExample {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop001", 8888)
    val result: DStream[(String, Int)] = lines.flatMap(_.split(" ")).map((_, 1))

    // 底层是：RDD的saveAsTextFile和foreachRDD
    result.saveAsTextFiles(prefix = System.currentTimeMillis() + "-", suffix = ".result")

  }
}
