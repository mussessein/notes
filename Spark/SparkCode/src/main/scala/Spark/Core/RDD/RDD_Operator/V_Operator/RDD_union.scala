package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * RDD的集合加减操作
 * 1. subtract：差集
 * 2. intersection:交集
 */
object RDD_union {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD_1: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))
    val listRDD_2: RDD[Int] = sc.makeRDD(List(4, 8, 3, 4, 7))

    val unionRDD: RDD[Int] = listRDD_1.subtract(listRDD_2)
    unionRDD.collect().foreach(println)
    val res: RDD[Int] = listRDD_1.intersection(listRDD_2)
    res.collect().foreach(println)

  }
}
