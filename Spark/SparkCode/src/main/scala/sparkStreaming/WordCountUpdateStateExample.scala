package sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}

object WordCountUpdateStateExample {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    /**
     * streamingContext:两个参数
     * sparkConf,Duration(采集周期):每过5s采集一次，仅记录当前的5s的数据
     */
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    // 使用updateStateByKey必须设置checkpoint
    ssc.checkpoint("./checkPointWordCountState/")

    val socketLineDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop001", 9999)

    val wordDStream: DStream[String] = socketLineDStream.flatMap(line => {
      line.split(" ")
    })
    val mapDStream: DStream[(String, Int)] = wordDStream.map((_, 1))


    val wordCountDStream = mapDStream.updateStateByKey(
      (values: Seq[Int], state: Option[Int]) => {
        // 获取当前Key传递进来的value的值
        val currentCount = values.sum

        // 获取Key以前状态state中的值
        val previousCount = state.getOrElse(0)

        // update state and return
        Some(currentCount + previousCount)
      }
    )


    wordCountDStream.print()

    /**
     * 启动采集器
     * 并且等待采集器终止
     * start：启动了一个Receiver（一个Thread接收线程，一直在运行，本质是一个makeRDD）
     *
     */
    ssc.start()

    ssc.awaitTermination()
  }
}
