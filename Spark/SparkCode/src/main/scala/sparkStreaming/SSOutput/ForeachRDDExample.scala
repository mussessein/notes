package sparkStreaming.SSOutput

import java.sql.DriverManager

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}

/**
 * 官方：一共有五种output
 * 1. println
 * 2. saveAsTextFiles
 * 3. saveAsObjectFiles
 * 4. saveAsHadoopFiles
 * 5. foreachRDD
 * 如果业务需要将SS中的数据
 * 保存到Redis，Hbase，MySQL中，可以用到此函数；
 */
object ForeachRDDExample {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkstreaming")
    val ssc = new StreamingContext(sparkConf, Seconds(5))

    ssc.checkpoint("./foreachRDD/")
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop001", 8888)
    val result: DStream[(String, Int)] = lines.flatMap(_.split(" ")).map((_, 1))

    /**
     * 将数据写入MySQL
     * 1. 创建一张表
     * 2. 写入sql
     */
    result.foreachRDD((rdd: RDD[(String, Int)], batchTime: Time) => {
      // 1. cache，很重要，提升性能
      rdd.cache()
      // 2. 保存到hdfs
      result.saveAsTextFiles(prefix = System.currentTimeMillis() + "-" + batchTime, suffix = ".result")
      // 3.保存到数据库，这里item是result
      rdd.coalesce(1, shuffle = false).foreachPartition(item => {
        // TODO:获取一个连接

        // TODO:保存数据的逻辑

        // TODO:返回连接
      })
      // 释放
      rdd.unpersist()
    })


    result.foreachRDD(rdd => {
      val connection = createConnection()
      rdd.foreach { record =>
        val sql = "insert into wordcount(word,count) values('" + record._1 + "'," + record._2 + ")"
        connection.createStatement().execute(sql)
      }
    })


    ssc.start()
    ssc.awaitTermination()

  }

  def createConnection() = {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql://localhost:3306/spark?useSSL=false", "root", "123456")
  }


}
