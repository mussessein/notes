package sparkStreaming.SparkStreamingFlume

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * pull方式整合
 */
object FlumePullWordCount {
  def main(args: Array[String]): Unit = {
    if (args.length != 2){
      System.err.println("Usage:FlumePushWordCount <hostname> <port>")
      System.exit(1)
    }
    val Array(hostname,port)= args
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("FlumePushWordCount")
    val ssc = new StreamingContext(sparkConf,Seconds(5))
    // 只有这里改一下
    val flumeStream: ReceiverInputDStream[SparkFlumeEvent] = FlumeUtils.createPollingStream(ssc,hostname,port.toInt)
    flumeStream.map(x=> new String(x.event.getBody().array()).trim)
      .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
