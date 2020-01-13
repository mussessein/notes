package sparkSQL.dataFrame

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * 使用sql操作DataSets
 *
 */
object SQLDataSets {
  val ss: SparkSession = SparkSession
    .builder()
    .appName("spark sql example")
    .master("local[*]")
    .getOrCreate()
  // 为了使得RDDS=>DataFrames

  import ss.implicits._

  // 创建 DF
  val sqlDF: DataFrame = ss.read.json("input/user.json")
  /**
   * 注册DF为一个全局视图 createGlobalTempView
   * 1.Global temporary view是跨session的
   */
  sqlDF.createGlobalTempView("people")
  sqlDF.show()
  //
  ss.sql("SELECT * FROM global_temp.people").show()
  // 跨session 调用视图
  ss.newSession().sql("SELECT * FROM global_temp.people").show()


}
