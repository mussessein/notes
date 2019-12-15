package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_sortBy {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 7, 6, 4, 3, 3, 2))
    /**
      * 参数：
      * f: (T) => K,排序规则
      * ascending: Boolean = true,默认升序
      * numPartitions: Int = this.partitions.length)，分区数
      */
    val sortRDD: RDD[Int] = listRDD.sortBy(x => x)
    sortRDD.collect().foreach(println)

  }
}
