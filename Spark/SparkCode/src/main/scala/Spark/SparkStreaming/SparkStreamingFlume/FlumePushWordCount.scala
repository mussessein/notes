package Spark.SparkStreaming.SparkStreamingFlume

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * SS整合flume
 * 将hostname和port以args的形式，配置在Configuration中
 */
object FlumePushWordCount {
  def main(args: Array[String]): Unit = {
    if (args.length != 2){
      System.err.println("Usage:FlumePushWordCount <hostname> <port>")
      System.exit(1)
    }
    val Array(hostname,port)= args
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("FlumePushWordCount")
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    val flumeStream: ReceiverInputDStream[SparkFlumeEvent] = FlumeUtils.createStream(ssc,hostname,port.toInt)
    flumeStream.map(x=> new String(x.event.getBody().array()).trim)
        .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
