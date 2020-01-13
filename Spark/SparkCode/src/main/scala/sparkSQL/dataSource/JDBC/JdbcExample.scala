package sparkSQL.dataSource.JDBC

import java.util.Properties

import org.apache.spark.sql.{DataFrame, SparkSession}

object JdbcExample {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local")
      .getOrCreate()
    /**
     * 读取数据的三种方法：
     * (1)jdbc直接读出数据
     */
    val jdbcDF: DataFrame = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://127.0.0.1:3306/db_user_auth?useSSL=false") // jdbc url
      .option("dbtable", "user")
      .option("user", "root") //username
      .option("password", "123456") //password
      .load()
    jdbcDF.show()
    /**
     * (2)创建连接，读出数据
     */
    val connectionProperties = new Properties()
    connectionProperties.put("user", "root")
    connectionProperties.put("password", "123456")
    val jdbcDF2: DataFrame = spark.read
      .jdbc("jdbc:mysql://127.0.0.1:3306/db_user_auth?useSSL=false", "user", connectionProperties)
    jdbcDF2.show()

    /**
     * (3)连接设置中，自定义视图
     */
    connectionProperties.put("customSchema", "id DECIMAL(38, 0), name STRING")
    val jdbcDF3 = spark.read
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)

    /**
     * 写入数据的三种方法：
     * (1)直接写入
     * (2)创建连接，写入
     * (3)创建连接，自定义视图
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

    jdbcDF.write
      .option("createTableColumnTypes", "name CHAR(64), comments VARCHAR(1024)")
      .jdbc("jdbc:postgresql:dbserver", "schema.tablename", connectionProperties)
  }
}
