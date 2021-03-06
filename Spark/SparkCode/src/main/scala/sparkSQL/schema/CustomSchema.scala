package sparkSQL.schema

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
 * 通过函数自定义schema
 */
object CustomSchema {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local[*]")
      .getOrCreate()
    //
    import org.apache.spark.sql.types._
    // sparkContext.textFile创建一个rdd
    val peopleRDD = spark.sparkContext.textFile("input/people.txt")
    // 自定义schema
    val schemaString = "name age"

    // 将自定义的schema解析出每一列：name，age；并声明每列的类型StringType,接收null值
    val fields: Array[StructField] = schemaString.split(" ")
      .map(fieldName => StructField(fieldName, StringType, nullable = true))
    // 返回视图
    val schema: StructType = StructType(fields)

    // 将rdd映射为Row对象
    val rowRDD: RDD[Array[String]] = peopleRDD
      .map(_.split(","))
    val attrRDD = rowRDD.map(attributes => Row(attributes(0), attributes(1).trim))

    // 通过RDD和schema创建DF对象
    val peopleDF = spark.createDataFrame(attrRDD, schema)

    // 创建局部视图
    peopleDF.createOrReplaceTempView("people")

    // 执行sql返回结果集(DF类型)
    val results: DataFrame = spark.sql("SELECT name FROM people")
    // +--------+
    // |    name|
    // +--------+
    // | Michael|
    // |    Andy|
    // |  Justin|
    // +----- --+
    import spark.implicits._

    // 遍历results结果集的每一列 操作
    results.map(attributes => "Name: " + attributes(0)).show()
    // +-------------+
    // |        value|
    // +-------------+
    // |Name: Michael|
    // |   Name: Andy|
    // | Name: Justin|
    // +-------------+
  }
}
