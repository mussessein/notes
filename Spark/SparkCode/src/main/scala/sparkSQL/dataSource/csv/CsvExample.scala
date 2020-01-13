package sparkSQL.dataSource.csv

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * 读取csv——和读取jdbc的第一种方法类似
 * option选项：
 * 1. option("header", "true")：跳过文件的首行
 * 2. option("delimiter", ";")：分割列的符号
 * 3. .schema(peopleStruct) ： 这里将数据结构化
 */
object CsvExample {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("scv example")
      .master("local")
      .getOrCreate()
    // 创建读取字段结构
    val peopleStruct: StructType = StructType(Array(
      StructField("name", StringType, true),
      StructField("age", IntegerType, true),
      StructField("job", StringType, true)
    ))

    val peopleCSV: DataFrame = spark
      .read
      .format("CSV")
      .option("header", "true")
      .option("delimiter", ";")
      .schema(peopleStruct) // 这里将数据结构化
      .load("hdfs://hadoop001:9000/data/resources/people.csv")
    peopleCSV.show()
    // 创建视图，从而使用sql语法
    peopleCSV.createOrReplaceTempView("people")
    peopleCSV.printSchema()
    spark.sql(
      """
        |SELECT
        |name
        |FROM
        |people
        |""".stripMargin).show()
    peopleCSV.write
      .mode("overwrite")
      .format("json")
      .save("peopleCSV.json")
  }
}
