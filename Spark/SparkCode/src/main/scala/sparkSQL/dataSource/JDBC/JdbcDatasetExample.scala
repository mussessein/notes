package sparkSQL.dataSource.JDBC

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

object JdbcDatasetExample {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .config("spark.some.config.option", "some-value")
      .getOrCreate()
    import spark.implicits._

    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", "jdbc:postgresql:dbserver") // jdbc url
      .option("dbtable", "schema.tablename") // table
      .option("user", "username") //username
      .option("password", "password") //password
      .load()
    /**
     * jdbc——读出数据
     */
    // 创建连接
    val connectionProperties = new Properties()
    connectionProperties.put("user", "root")
    connectionProperties.put("password", "123456")
    // 读取表 到 DF
    val jdbcDF2: DataFrame = spark.read
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
    //
    connectionProperties.put("customSchema", "id DECIMAL(38, 0), name STRING")
    val jdbcDF3 = spark.read
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)

    /**
     * save数据到jdbc
     */
    jdbcDF.write
      .format("jdbc")
      .option("url", "jdbc:postgresql:dbserver")
      .option("dbtable", "schema.tablename")
      .option("user", "username")
      .option("password", "password")
      .save()

    jdbcDF2.write
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)

    // Specifying create table column data types on write
    jdbcDF.write
      .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
  }
}
