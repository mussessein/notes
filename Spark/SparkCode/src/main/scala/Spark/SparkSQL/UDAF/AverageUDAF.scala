package Spark.SparkSQL.UDAF

import org.apache.spark.sql.Row
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types.{DataType, DoubleType, LongType, StructType}

/**
  * 自定义聚合函数(弱类型:操作的数据是基本数据类型，而不是对象)
  * 求年龄平均值
  */
class AverageUDAF extends UserDefinedAggregateFunction{

  // 输入数据的结构:LongType的age
  override def inputSchema: StructType = {
    new StructType().add("age",LongType)
  }
  // (计算时)数据的结构：需要sun和count（avg=sum/count）
  override def bufferSchema: StructType = {
    new StructType().add("sum",LongType).add("count",LongType)
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
    buffer(0)=0L  // sum
    buffer(1)=0L  // count
  }
  // 更新缓冲区
  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    // sum
    buffer(0) = buffer.getLong(0)+input.getLong(0)
    // count每次更新+1
    buffer(1) = buffer.getLong(1)+1

  }
  // 多节点缓冲区，合并
  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {
    // 将每个buffer的第一个字段，相加，得到sum
    buffer1(0) = buffer1.getLong(0)+buffer2.getLong(0)
    // 第二个字段为count
    buffer1(1) = buffer1.getLong(1)+buffer2.getLong(1)

  }
  // 计算
  override def evaluate(buffer: Row): Any = {
    buffer.getLong(0).toDouble / buffer.getLong(1)
  }
}
