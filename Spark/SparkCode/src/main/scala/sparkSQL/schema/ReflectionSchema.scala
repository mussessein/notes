package sparkSQL.schema

import org.apache.spark.sql.SparkSession

/**
 * 通过反射操作schema
 * 将数据映射为实体类
 * 通过反射读取
 */
object ReflectionSchema {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import spark.implicits._
    /**
     * 数据读取，反射封装为bean
     */
    val peopleDF = spark.sparkContext
      .textFile("examples/src/main/resources/people.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()
    peopleDF.createOrReplaceTempView("people")
    val teenagersDF = spark.sql("SELECT name, age FROM people WHERE age BETWEEN 13 AND 19")
    teenagersDF.map(teenager => "Name: " + teenager(0)).show()
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+

  }
}
