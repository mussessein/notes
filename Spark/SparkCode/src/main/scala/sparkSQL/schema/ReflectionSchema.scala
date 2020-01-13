package sparkSQL.schema

import org.apache.spark.sql.{DataFrame, SparkSession}

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
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    /**
     * 数据读取，反射封装为bean
     */
    val peopleDF = spark.sparkContext
      .textFile("input/people.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()
    peopleDF.createOrReplaceTempView("people")
    val result: DataFrame = spark.sql("select * from people")
    result.show()
    //    +------+---+
    //    |  name|age|
    //    +------+---+
    //    |Justin| 19|
    //    |  Andy| 29|
    //    +------+---+
    val teenagersDF = spark.sql("SELECT name, age FROM people WHERE age BETWEEN 13 AND 19")
    teenagersDF.map(teenager => "Name: " + teenager(0)).show()
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+

  }
}
