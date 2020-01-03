package sparkSQL.dataSource.Hive

import java.io.File

import org.apache.spark.sql.{DataFrame, SparkSession}

/**
 * spark2.x操作Hive
 * 不再有HiveContext，也不再使用HQL
 * 统一使用SparkSession，统一使用DF的SQL
 */
object HiveExample {
  def main(args: Array[String]): Unit = {
    val warehouseLocation = new File("spark-warehouse").getAbsolutePath
    // println(warehouseLocation)
    val spark: SparkSession = SparkSession
      .builder()
      .appName("Hive example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import spark.implicits._
    import spark.sql

    sql("CREATE TABLE IF NOT EXISTS src (key INT, value STRING) USING hive")
    sql("LOAD DATA LOCAL INPATH 'input/hive_db/kv1.txt' INTO TABLE src")
    // 直接使用
    sql("SELECT * FROM src").show()
    // +---+-------+
    // |key|  value|
    // +---+-------+
    // |238|val_238|
    // | 86| val_86|
    // |311|val_311|
    // ...
    // 结果集为 DataFrame
    val sqlDF: DataFrame = sql("SELECT key, value FROM src WHERE key < 10 ORDER BY key")
  }
}
