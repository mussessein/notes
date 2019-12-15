package Spark.Core.RDD.RDD_Data

import java.sql.DriverManager

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * RDD_JDBC链接
  */
object RDD_MySql_Read {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val DRIVER = "com.mysql.jdbc.Driver"
    val URL = "jdbc:mysql://master:3306/test"
    val USERNAME = "root"
    var PASSWD = "123456"
    val sql = "select name,age from user where id >= ? and id <= ?"

    /**
      * 创建jdbcRDD，访问数据库
      * 读数据
      */
    val res = new JdbcRDD(
      sc,
      () => {
        // 获取数据库连接对象
        Class.forName(DRIVER)
        DriverManager.getConnection(URL, USERNAME, PASSWD)
      },
      sql,
      1,
      3,
      2,
      (resultSet) => {
        println(resultSet.getString(1) + "," + resultSet.getString(2))
      }
    )
    res.collect()
    sc.stop()

  }
}
