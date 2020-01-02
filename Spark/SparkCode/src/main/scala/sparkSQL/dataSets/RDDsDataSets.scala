package sparkSQL.dataSets

import org.apache.spark.sql.{DataFrame, SparkSession}
/**
 * RDDS操作 DF
 *
 */
object RDDsDataSets {
  val spark: SparkSession = SparkSession
    .builder()
    .appName("spark sql example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()
  // 为了使得RDDS=>DataFrames
  import spark.implicits._
  // 创建 DF
  val rddDF: DataFrame = spark.read.json("input/user.json")
  // 创建视图：将内存df数据映射成一张表
  rddDF.createOrReplaceTempView("people") // 创建视图
  rddDF.show()
  // 选择列
  rddDF.select("name").show()
  // +-------+
  // |   name|
  // +-------+
  // |Michael|
  // |   Andy|
  // | Justin|
  // +-------+
  rddDF.select($"name", $"age" + 1).show()
  // +-------+---------+
  // |   name|(age + 1)|
  // +-------+---------+
  // |Michael|     null|
  // |   Andy|       31|
  // | Justin|       20|
  // +-------+---------+

  // drop视图
  spark.catalog.dropTempView("people")


}
