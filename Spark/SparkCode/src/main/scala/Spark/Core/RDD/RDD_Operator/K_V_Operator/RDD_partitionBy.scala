package Spark.Core.RDD.RDD_Operator.K_V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object RDD_partitionBy {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD_1: RDD[(Int, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc"), (4, "ddd"), (5, "eee")))
    /**
      * HashPartitioner:通过key的值分区，key.hashCode/partitions
      * 也就是说：1/2=1,3/2=1,5/2=1，分在一个区；2/2=0,4/2=0，分在一个区
      */
    val hashRDD: RDD[(Int, String)] = listRDD_1.partitionBy(new HashPartitioner(2))
    hashRDD.collect().foreach(println)

  }
}
