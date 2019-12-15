package Spark.SparkSQL.UDAF

import org.apache.spark.SparkConf
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession, TypedColumn}

object AverageAggregator_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("UDAF")
    val spark: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
    import spark.implicits._
    val averageAggregator = new AverageAggregator

    /**
      * 此聚合函数,传入的是对象
      * 需要转换成查询列
      */
    val avgAge: TypedColumn[UserBean, Double] = averageAggregator.toColumn.name("avgAge")
    /**
      * 强类型,需要DataSet查询
      * 并使用DataSet的select列方法
      */
    val frame: DataFrame = spark.read.json("input/user.json")
    val userDs: Dataset[UserBean] = frame.as[UserBean]
    userDs.select(avgAge).show()
    spark.stop()
  }
}
