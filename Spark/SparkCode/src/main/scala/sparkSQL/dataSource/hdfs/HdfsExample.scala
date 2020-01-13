package sparkSQL.dataSource.hdfs

import org.apache.spark.sql.{DataFrame, SparkSession}

object HdfsExample {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .master("local[*]")
      .appName("hdfs test")
      .getOrCreate()

    val peopleDF: DataFrame = spark.read.json("hdfs://hadoop001:9000/data/resources/people.json")
    peopleDF.show()
    peopleDF.createOrReplaceTempView("people")
    import spark.implicits._
    val res: DataFrame = peopleDF.select($"name", $"age" + 1)
    res.show()
    //res.write.save("hdfs://hadoop001:9000/data/resources/res/")
  }
}
