package sparkStreaming

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * 完成词频统计，将数据写入MySQL
 */
object ForeachRDD {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    /**
     * streamingContext:两个参数
     * sparkConf,Duration(采集周期):每过5s采集一次，仅记录当前的5s的数据
     */
    val ssc = new StreamingContext(sparkConf,Seconds(5))

    ssc.checkpoint(".")
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("127.0.0.1",8888)
    val result: DStream[(String, Int)] = lines.flatMap(_.split(" ")).map((_,1))

    /**
     * 将数据写入MySQL
     * 1. 创建一张表
     * 2. 写入sql
     */
    result.foreachRDD(rdd=>{
      val connection = createConnection()
      rdd.foreach{record=>
        val sql="insert into wordcount(word,count) values('"+record._1+"',"+record._2+")"
        connection.createStatement().execute(sql)
      }
    })




    ssc.start()
    ssc.awaitTermination()

  }
  def createConnection()={
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/spark","root","123456")
  }




}
