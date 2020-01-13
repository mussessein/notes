package sparkSQL.UDAF

import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, IntegerType, LongType, StringType, StructField, StructType}

/**
 * Untyped User-Defined Aggregate Functions
 * 自定义聚合函数——弱类型:操作的数据是基本数据类型，而不是对象:求年龄平均值
 * 继承UserDefinedAggregateFunction，实现方法：
 * 1. inputSchema：传入数据类型
 * 2. bufferSchema：聚合缓冲区数据类型
 * 3. dataType：返回值数据类型
 * 4. deterministic：是否始终相同输入返回相同输出
 * 5. initialize：初始化缓冲区
 * 6. update：每次新数据来了，对缓冲区的操作
 * 7. merge：多节点缓冲区，合并
 * 8. evaluate：计算，返回结果
 */
class AverageUDAF extends UserDefinedAggregateFunction {

  // 输入数据的结构:LongType的age
  override def inputSchema: StructType = {
    new StructType().add("age", LongType)
  }

  // (计算时)数据的结构：需要sun和count（avg=sum/count）
  override def bufferSchema: StructType = {
    new StructType().add("sum", LongType).add("count", LongType)
  }

  // 返回类型:Double
  override def dataType: DataType = DoubleType

  // 函数是否稳定
  override def deterministic: Boolean = true

  /**
   * 计算之前，缓冲区初始化
   * 每个buffer都有两个字段：buffer1，buffer2
   */
  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0) = 0L // sum
    buffer(1) = 0L // count
  }

  // 更新缓冲区
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    // sum
    buffer(0) = buffer.getLong(0) + input.getLong(0)
    // count每次更新+1
    buffer(1) = buffer.getLong(1) + 1

  }

  // 多节点缓冲区，合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 将每个buffer的第一个字段，相加，得到sum
    buffer1(0) = buffer1.getLong(0) + buffer2.getLong(0)
    // 第二个字段为count
    buffer1(1) = buffer1.getLong(1) + buffer2.getLong(1)

  }

  // 计算
  override def evaluate(buffer: Row): Any = {
    buffer.getLong(0).toDouble / buffer.getLong(1)
  }
}

object AverageUDAF {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("udaf example")
      .master("local")
      .getOrCreate()

    val udaf = new AverageUDAF
    // 注册聚合函数
    spark.udf.register("avgAge", udaf)
    val peopleStruct: StructType = StructType(Array(
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)
    ))
    val peopleDF: DataFrame = spark
      .read
      .schema(peopleStruct)
      .option("seq", ",")
      .text("hdfs://hadoop001:9000/data/resources/people.txt")
    peopleDF.show()
    peopleDF.createOrReplaceTempView("people")
    spark.sql("select age from people").show()
    spark.sql("select avgAge(age) from people").show()
    spark.stop()
  }
}
