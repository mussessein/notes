package sparkSQL.dataFrame

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * DF的两种操作：
 * 1. 通过DF的API操作数据
 * 2. 通过为数据增加视图，然后用sql语法操作
 *
 */
object RDDsDataSets {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local[*]")
      .getOrCreate()
    // 为了使得RDDS=>DataFrames
    import spark.implicits._

    /**
     * API语法操作视图
     */
    // 创建 DF
    val rddDF: DataFrame = spark.read.json("input/user.json")
    // 创建视图：将内存df数据映射成一张表
    rddDF.createOrReplaceTempView("people") // 创建视图
    rddDF.show()
    println("========================API==========================")
    // 选择列
    rddDF.select("name").show()
    rddDF.select($"name", $"age" + 1).show()
    // drop视图
    spark.catalog.dropTempView("people")

    /**
     * sql语法操作视图
     * 注册DF为一个全局视图 createGlobalTempView
     * 1.Global temporary view是跨session的
     *
     */
    println("========================SQL==========================")
    rddDF.createGlobalTempView("person")
    spark.sql("SELECT * FROM global_temp.person").show()
    // 跨session 调用视图
    spark.newSession().sql("SELECT * FROM global_temp.person").show()

  }
}
