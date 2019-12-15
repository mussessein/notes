package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * distinct（shuffle）
  * 数据去重
  * shuffle性能比较低
  */
object RDD_distinct {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    // 创建RDD，分成3个分区
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 2, 2, 4, 4, 4, 4, 8, 8, 8))
    /**
      * distinct将数据进行打乱，重组（shuffle）
      * map：处理完之后的数据，还放进对应的分区。
      * 放进分区中，可能有的分区有多个数据，可能有的分区没有数据
      * 将结果保存在本地，可以看分区
      */
    val distinctRDD: RDD[Int] = listRDD.distinct(8)

    /**
      * 1. 打印控制台
      * 2. 保存到文件
      */
    distinctRDD.collect().foreach(println)
    distinctRDD.saveAsTextFile("output")
  }
}
