package sparkStreaming.window

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object WordCountWindow {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val socketLineDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop001", 9999)
    val mapDStream: DStream[(String, Int)] = socketLineDStream
      .flatMap(_.split(" "))
      .map((_, 1))

    /**
     * window operation
     * reduceFunc：实际处理数据的函数；
     * windowDuration：窗口大小
     * slideDuration：滑动大小，默认值是：batch interval,可能与上个窗口有重叠,这样就可能两个窗口处理的数据存在重复；
     */
    val wordsRes: DStream[(String, Int)] = mapDStream.reduceByKeyAndWindow(((_: Int) + (_: Int)), Seconds(10), Seconds(5))

    // 打印结果
    wordsRes.print()
    ssc.start()
    ssc.awaitTermination()
    // 关闭
    ssc.stop(true, true)
  }
}
