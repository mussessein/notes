package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_glom {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    // 创建RDD，分成3个分区
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 3)
    /**
      * glom算子：
      * 将一个分区的数据，放进一个数组中
      */
    val glomRDD: RDD[Array[Int]] = listRDD.glom()
    glomRDD.collect().foreach(array => {
      println(array.mkString(","))
    })

  }

}
