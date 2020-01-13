package sparkCore.RDD.RDD_Data

import java.sql.DriverManager

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_MySql_Write {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val DRIVER = "com.mysql.jdbc.Driver"
    val URL = "jdbc:mysql://master:3306/test"
    val USERNAME = "root"
    var PASSWD = "123456"

    val dataRDD: RDD[(String, Int)] = sc.makeRDD(List(("niu", 22), ("qing", 17)))

    /**
      * foreachPartition：对每个分区的操作
      * foreach：对每条数据的操作
      * foreachPartition以外的逻辑，都是Driver中执行，必须可序列化
      * Connection不可序列化，只能放在foreachPartition的逻辑内
      */
    // datas每个分区，data分区内每个数据
    dataRDD.foreachPartition(datas => {
      Class.forName(DRIVER)
      val connection = DriverManager.getConnection(URL, USERNAME, PASSWD)
      val sql = "insert into user (name,age) values (?,?)"

      datas.foreach {
        case (username, age) => {
          val statement = connection.prepareStatement(sql)
          statement.setString(1, username)
          statement.setInt(2, age)
          statement.execute()
          statement.close()

        }
          connection.close()
      }
    })
    sc.stop()
  }
}
