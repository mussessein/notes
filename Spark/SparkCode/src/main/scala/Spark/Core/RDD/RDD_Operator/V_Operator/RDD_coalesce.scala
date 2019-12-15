package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * coalesce缩减分区数
  * coalesce本意：合并
  * repartition底层调用coalesce，并shuffle
  */
object RDD_coalesce {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    // 创建RDD，分成3个分区
    val listRDD: RDD[Int] = sc.makeRDD(1 to 16, 4)
    listRDD.saveAsTextFile("output1")
    /**
      * 三个参数：
      * numPartitions：合并后剩余分区数
      * shuffle:是否shuffle，默认false
      * partitionCoalescer
      */
    val coalRDD: RDD[Int] = listRDD.coalesce(2)
    coalRDD.collect().foreach(println)
    coalRDD.saveAsTextFile("output2")

  }
}
