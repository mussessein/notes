package sparkSQL.UDAF

import org.apache.spark.sql.{DataFrame, Dataset, Encoder, Encoders, SparkSession, TypedColumn}
import org.apache.spark.sql.expressions.Aggregator


/**
 * Type-Safe User-Defined Aggregate Functions
 * 自定义聚合函数——强类型:操作的数据是引用类型，对象
 * 继承Aggregate，声明三个泛型【输入类型，Buffer类型，返回类型Double】
 * 重写方法：
 *
 */
class AverageAggregator extends Aggregator[UserBean, AvgBuffer, Double] {
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
    b.sum = b.sum + a.age
    b.count = b.count + 1
    b
  }

  /**
   * 缓冲区的合并操作
   */
  override def merge(b1: AvgBuffer, b2: AvgBuffer): AvgBuffer = {
    b1.sum = b1.sum + b2.sum
    b1.count = b1.count + b2.count
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

object AverageAggregator {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession
      .builder()
      .appName("spark sql example")
      .master("local")
      .getOrCreate()
    import spark.implicits._
    val averageAggregator = new AverageAggregator

    /**
     * 此聚合函数,传入的是对象
     * 需要转换成查询列
     */
    val avgAge: TypedColumn[UserBean, Double] = averageAggregator.toColumn.name("avgAge")
    /**
     * 强类型,需要DataSet查询
     * 并使用DataSet的select列方法
     */
    val frame: DataFrame = spark.read.json("input/user.json")
    val userDs: Dataset[UserBean] = frame.as[UserBean]
    userDs.select(avgAge).show()
    spark.stop()
  }
}