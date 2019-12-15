package Spark.SparkSQL.DataFrame

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, SparkSession}

object DF_Test {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
    val ss: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    val frame: DataFrame = ss.read.json("input/user.json")
    //frame.show()
    frame.createOrReplaceTempView("user") // 创建视图
    ss.sql("select * from user").show()
    ss.stop()
  }
}
