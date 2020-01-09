# Spark Streaming

Hadoop的MapReduce及Spark SQL等只能进行离线计算，无法满足实时性要求较高的业务需求，例如实时推荐，实时网站性能分析等，流式计算可以解决这些问题。

目前三种流式计算框架：storm,spark Streaming和Samza

将输入源的数据，通过Spark的抽象方法：mao、reduce、join、window等进行计算；

结果可以保存入HDFS、数据库等；

![](C:/Users/whr/Desktop/Spark/image/streaming.png)

## DStream

DStream高度抽象的离散流；

DStream本质上表示RDD的序列。任何对DStream的操作都会转变为对底层RDD的操作。

![DStream](C:/Users/whr/Desktop/Spark/image/DStream.png)

## SS整合Flume

### （1）Push

Flume从netcat中读到数据，通过sink传递给SparkStreaming

1. 导入依赖

   ```xml
   1. spark-core
   2. flume
   3. spark-streaming-flume_2.11 
   4. spark-streaming-flume-sink_2.11
   ```

2. Flume配置：

   ```properties
   # Flume push到Streaming
   a1.sources = r1
   a1.channels = c1
   a1.sinks = k1
   # nc localhost 9999输入数据
   a1.sources.r1.type=netcat
   a1.sources.r1.bind=localhost
   a1.sources.r1.port=9999
   # sink将数据从8888端口输出给SparkStreaming
   a1.sinks.k1.type = avro
   a1.sinks.k1.hostname = master
   a1.sinks.k1.port = 8888
   a1.channels.c1.type = memory
   a1.channels.c1.capacity = 1000
   a1.channels.c1.transactionCapacity=100
   a1.sources.r1.channels=c1
   a1.sinks.k1.channel=c1
   ```

3. 编写代码

   ```scala
   object FlumePushWordCount {
     def main(args: Array[String]): Unit = {
       val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("FlumePushWordCount")
       val ssc = new StreamingContext(sparkConf,Seconds(5))
       val flumeStream: ReceiverInputDStream[SparkFlumeEvent] = FlumeUtils.createStream(ssc,"master",8888)
       flumeStream.map(x=> new String(x.event.getBody().array()).trim)
           .flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
       ssc.start()
       ssc.awaitTermination()
     }
   }
   ```

4. 先启动SparkStreaming程序，再启动Flume，再启动netcat，从netcat输入数据

   Flume的RpcClient启动前提，对应端口要先被监听，所以需要先启动应用程序

   ```shell
   ./bin/flume-ng agent --conf conf --conf-file ./conf/demo/flume-push-SparkStreaming.conf --name a1 -Dflume.root.logger=INFO,console
   ```

### （2）Pull

1. Flume配置

   ```properties
   # Flume push到Streaming
   a1.sources = r1
   a1.channels = c1
   a1.sinks = k1
   # nc localhost 9999输入数据
   a1.sources.r1.type=netcat
   a1.sources.r1.bind=localhost
   a1.sources.r1.port=9999
   # 使用SparkSink
   a1.sinks.k1.type = org.apache.spark.streaming.flume.sink.SparkSink
   a1.sinks.k1.hostname = master
   a1.sinks.k1.port = 8888
   a1.channels.c1.type = memory
   a1.channels.c1.capacity = 1000
   a1.channels.c1.transactionCapacity=100
   a1.sources.r1.channels=c1
   a1.sinks.k1.channel=c1
   ```

2. 编写scala代码

   ```scala
   FlumeUtils的 createStream改为createPollingStream
   ```

3. 先启动Flume，后启动SparkStreaming，再启动netcat

   ```shell
   ./bin/flume-ng agent --conf conf --conf-file ./conf/demo/flume-pull-SparkStreaming.conf --name a1 -Dflume.root.logger=INFO,console
   ```

# Structured Streaming

spark2.0 将要代替DStream的structured streaming

以下内容摘自 spark2.3.1官方文档；

![](C:/Users/whr/Desktop/Spark/image/ss1.PNG)

- Structured Streaming将输入数据流视为"输入表"。流上到达的每个数据项都像是将新行附加到输入表中；表的行数随着数据量的增大而增加；

![](C:/Users/whr/Desktop/Spark/image/ss2.png)

- 对输入的查询将生成"结果表"。 在每个触发间隔（例如，每1秒钟），新行将附加到输入表中，并最终更新结果表。 无论何时更新结果表，我们都希望将更改后的结果行写入外部接收器。

![](C:/Users/whr/Desktop/Spark/image/ss3.png)

- 上图例子

## Input Sources

- File source：读取写入目录的文件作为数据流。 支持的文件格式为text，csv，json，orc，parquet。
- Kafka source：从kafka中读数据
- 

## output mode

参考源码

- Append：每次只将"输入表"中的新行，写入sink，这种模式不能用于聚合；
- Complete：每次都将"输入表"所有行，写入sink，这种方法用于聚合的查询；
- Update：说是，不使用聚合，就跟Append一样，没太懂；