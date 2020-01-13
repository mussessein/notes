package sparkStreaming.SSCheckPoint

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object CheckPointExample {
  val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
  val checkpointPath = "./checkPointExample/"

  /**
   * 创建checkpoint，高可用函数
   *
   * @return
   */
  def functionToCreateContext(): StreamingContext = {
    val ssc = new StreamingContext(sparkConf, Seconds(5)) // new context
    /**
     * 业务处理逻辑
     */
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

    // set checkpoint directory
    ssc.checkpoint(checkpointPath)
    ssc
  }

  def main(args: Array[String]): Unit = {
    /**
     * 主函数：
     * 创建或者拿到 ssc
     * 而不是直接创建，如果已存在ssc，直接从checkpoint中获得之前的ssc的状态，
     */
    val ssc: StreamingContext = StreamingContext.getOrCreate(checkpointPath, functionToCreateContext)
    ssc.start()
    ssc.awaitTermination()
  }
}
