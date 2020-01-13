package sparkStreaming.SSKafka

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.streaming.kafka010.KafkaUtils
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 从Kafka Topic 中 读取数据，使用SparkStreaming进行实时数据处理
 */
object KafkaUpdateStreaming {

  def main(args: Array[String]): Unit = {

    // 创建SparkConf, 读取和设置应用的配置信息
    val sparkConf = new SparkConf()
      .setAppName("WordCountStreaming")
      // 在本地模式下，启动3个Thread进行运行
      .setMaster("local[3]")
    // 创建SparkContext上下文对象
    val sc = SparkContext.getOrCreate(sparkConf)

    // 设置日志级别
    sc.setLogLevel("WARN")

    /**
     * 创建SpakrStreaming程序的入口，用户读取Streaming Data封装到DStream中
     * 底层：按照时间间隔进行划分成很多批次，设置batch interval
     */
    val ssc = new StreamingContext(sc, Seconds(5))
    // 设置检查点目录
    ssc.checkpoint("/datas/sparkstreaming/stateKafka/00000000")

    /**
     * Step 1: 接收数据源的额数据
     * read data from network socket
     */
    // KAFKA CLUSTER
    val kafkaParams: Map[String, String] = Map(
      "kafka.bootstrap.servers" -> "hadoop001:9092"
    )

    val topics: Set[String] = Set("spark")
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      PreferConsistent,
      Subscribe[String, String](topics, kafkaParams)
    )
    val linesDStream: DStream[String] = stream.map(record => record.value)
    /**
     * Step 2: 处理接收的数据
     */
    // Split each line into words
    val pairsDStream: DStream[(String, Int)] = linesDStream
      .flatMap(_.split(" "))
      .map((_, 1))

    /**
     * updateStateByKey更新函数：
     * 有两个参数：Seq，state
     * Seq：一个集合，存着RDD的多个value。即：<hello,(1,1,1,1)>
     * state：是key的状态信息，可有可无，所以是Option类型，使用getOrElse获取
     */
    val wordCountDStream: DStream[(String, Int)] = pairsDStream.updateStateByKey(
      (values: Seq[Int], state: Option[Int]) => {
        // 获取当前Key传递进来的value的值
        val currentCount: Int = values.sum

        // 获取Key以前状态state中的值
        val previousCount: Int = state.getOrElse(0)

        // update state and return
        Some(currentCount + previousCount)
      }
    )

    /**
     * Step 3: 输出处理的结果数据
     * output data To console
     */
    wordCountDStream.print() // Print the first ten elements for each RDD

    /**
     * 对于实时流式数据处理应用来说，编程以后以来需要作如下两点
     * 第一点：需要启动应用（不是运行程序）
     * 第二点：流式应用一旦运行起来，一致在运行，除非程序被认为停止或者程序出异常
     */
    // Start the execution of the streams
    ssc.start()
    // Wait for the execution to stop
    ssc.awaitTermination()

    // 关闭
    ssc.stop(true, true)
  }

}
