package Spark.SparkSQL.UDAF

import org.apache.spark.sql.{Encoder, Encoders}
import org.apache.spark.sql.expressions.Aggregator

/**
  * 自定义聚合函数
  * (强类型:操作的数据是引用类型，对象)
  */
case class UserBean(name:String,age:BigInt){}
// case class 默认val,必须改为var
case class AvgBuffer(var sum:BigInt,var count:Int){}
/**
  * 继承Aggregate，声明三个泛型【输入类型，Buffer类型，返回类型Double】
  * 实现方法
  */
class AverageAggregator extends Aggregator[UserBean,AvgBuffer,Double] {
  /**
    * 初始化一个缓冲对象
    */
  override def zero: AvgBuffer = {
    AvgBuffer(0, 0)
  }
  /**
    * reduce方法
    * 聚合:将输入的对象的数量和年龄统计起来
    */
  override def reduce(b: AvgBuffer, a: UserBean): AvgBuffer = {
    b.sum=b.sum+a.age
    b.count=b.count+1
    b
  }
  /**
    * 缓冲区的合并操作
    */
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum+b2.sum
    b1.count = b1.count+b2.count
    b1
  }
  /**
    *
    */
  override def finish(reduction: AvgBuffer): Double = {
    reduction.sum.toDouble / reduction.count
  }
  /**
    * 编码
    */
  override def bufferEncoder: Encoder[AvgBuffer] = Encoders.product
  override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}
