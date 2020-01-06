package sparkSQL.dataSource.parquetFile

import org.apache.spark.sql.{DataFrame, SparkSession}

object ParquetTest {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local[*]")
      .getOrCreate()
    import spark.implicits._
    /**
     * 读取外部数据
     * 写入parquet
     */
    val peopleDF = spark.read.json("input/user.json")
    peopleDF.write.parquet("input/user.parquet")
    /**
     * 读取数据为RDD
     * 创建视图
     * 执行sql，返回结果集
     * map处理结果集-->show
     */
    val parquetFileDF: DataFrame = spark.read.parquet("input/user.parquet")
    parquetFileDF.createOrReplaceTempView("parquetFile")
    val results = spark.sql("SELECT name FROM parquetFile WHERE age BETWEEN 13 AND 19")
    results.map(attributes => "Name: " + attributes(0)).show()
    // +------------+
    // |       value|
    // +------------+
    // |Name: Justin|
    // +------------+
  }
}
