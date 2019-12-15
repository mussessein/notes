package Spark.SparkSQL.JDBC

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object JDBC_Read {
  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("JDBC")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val frame: DataFrame = spark.read.format("jdbc")
      .option("url", "jdbc:mysql://master:3306/test")
      .option("dbtable", "user")
      .option("user", "root")
      .option("password", "123456")
      .load()
    frame.show()
    spark.stop()
  }

}
