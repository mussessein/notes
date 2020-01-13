package sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 用SparkStreaming执行WordCount
 * 先使用$ nc -l -p 9999 监听端口，再启动程序
 */
object WordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    /**
     * streamingContext:两个参数
     * sparkConf,Duration(采集周期):每过5s采集一次，仅记录当前的5s的数据
     */
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint("./WordCountState/")
    /**
     * 从指定端口采集数据
     * socketTextStream:监听tcp端口，接收utf8字节流，并以\n分割，接收
     */
    val socketLineDStream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop001", 9999)
    /**
     * 分解数据:(扁平化)
     * 将每行数据,分割成单词
     * 与RDD一样
     */
    val wordDStream: DStream[String] = socketLineDStream.flatMap(line => {
      line.split(" ")
    })
    val mapDStream: DStream[(String, Int)] = wordDStream.map((_, 1))
    val reduceDStream: DStream[(String, Int)] = mapDStream.reduceByKey(_ + _)

    // 打印结果
    reduceDStream.print()

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
