package sparkSQL.schema

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * 视图创建：
 * 1. 通过DF创建
 * (1)createOrReplaceTempView:基于SparkSession,生命周期与当前sparkSession关联，不能跨session
 * (2)createGlobalTempView:基于Spark Application，可以跨session
 */
object CreateSchema {
  def main(args: Array[String]): Unit = {
    val ss: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local[*]")
      .getOrCreate()
    // 为了使得RDDS=>DataFrames
    import ss.implicits._
    // 创建 DF
    val rddDF: DataFrame = ss.read.json("input/user.json")
    // 创建视图：将内存df数据映射成一张表
    rddDF.createOrReplaceTempView("people") // 创建视图

  }
}
