package Spark.Core.Accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}


object Accumulator_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Accumulator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

    /**
      * 创建自定义累加器
      * 注册累加器
      */
    val accumulator: LongAccumulator = sc.longAccumulator
    rdd.foreach {
      case i => {
        accumulator.add(i)
      }
    }
    println(accumulator.value)
    sc.stop()
  }


}
