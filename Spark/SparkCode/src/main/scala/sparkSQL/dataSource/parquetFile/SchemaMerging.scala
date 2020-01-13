package sparkSQL.dataSource.parquetFile

import org.apache.spark.sql.SparkSession

/**
 * schema合并
 */
object SchemaMerging {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    /**
     * 创建两个DF，存到指定路径
     */
    val squaresDF = spark.sparkContext.makeRDD(1 to 5).map(i => (i, i * i)).toDF("value", "square")
    squaresDF.write.parquet("hdfs://hadoop001:9000/data/test_table/key=1")

    val cubesDF = spark.sparkContext.makeRDD(6 to 10).map(i => (i, i * i * i)).toDF("value", "cube")
    cubesDF.write.parquet("hdfs://hadoop001:9000/data/test_table/key=2")

    // Read the partitioned table
    val mergedDF = spark.read.option("mergeSchema", "true").parquet("hdfs://hadoop001:9000/data/test_table")
    mergedDF.printSchema()

    // The final schema consists of all 3 columns in the Parquet files together
    // with the partitioning column appeared in the partition directory paths
    // root
    //  |-- value: int (nullable = true)
    //  |-- square: int (nullable = true)
    //  |-- cube: int (nullable = true)
    //  |-- key: int (nullable = true)
  }
}
