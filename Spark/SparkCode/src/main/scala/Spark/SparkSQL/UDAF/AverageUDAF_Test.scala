package Spark.SparkSQL.UDAF

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf}

object AverageUDAF_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UDAF")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val udaf = new AverageUDAF

    // 注册聚合函数
    spark.udf.register("avgAge",udaf)
    val frame: DataFrame = spark.read.json("input/user.json")
    frame.createOrReplaceTempView("user")
    spark.sql("select age from user").show()
    spark.sql("select avgAge(age) from user").show()
    spark.stop()
  }
}
