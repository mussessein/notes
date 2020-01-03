package structuredStreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.ProcessingTime
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
 * 数据源：Files
 * 流式数据处理：
 * 1. 读数据
 * 2. 处理数据
 * 3. 输出
 */
object StructuredFiles {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.
      master("local[2]").
      appName("StructuredFiles").
      getOrCreate()
    // 构建视图（列名，列类型，是否可以为null）
    val schemaExp = StructType(
      StructField("name", StringType, false) ::
        StructField("city", StringType, true)
        :: Nil
    )
    //val userSchema = new StructType().add("name", "string").add("age", "integer")
    /**
     * 读数据：（构建"输入表"）
     * 1 spark.readStream读取数据
     * 2 .format("json")选择数据源类型
     * 3 .schema(schemaExp)定义视图
     * 4 .load("file:///tmp/dir")文件路径
     */
    val words = spark
      .readStream
      .format("json")
      .schema(schemaExp)
      .load("file:///tmp/dir")

    /**
     * 使用DF对流数据做中间处理：
     */
    val wordCounts = words.groupBy("name").count()

    /**
     * 输出：
     * 1. wordCounts.writeStream处理结果 RDD调用writeStream方法
     * 2. outputMode("complete")输出模式：Append，Complete，Update
     * 3. .format("console")输出位置console，memory，parquet，foreach
     * 4. trigger 触发器，每5秒触发一次输出
     * 5. start 启动输出
     */
    val query = wordCounts.writeStream
      .outputMode("complete")
      //console,parquet,memory,foreach 四种
      .format("console")
      .trigger(ProcessingTime("5 seconds")) //这里就是设置定时器了
      .start()
    // 任务完成中止
    query.awaitTermination()
  }
}
