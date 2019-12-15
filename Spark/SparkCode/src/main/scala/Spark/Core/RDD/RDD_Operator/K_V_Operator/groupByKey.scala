package Spark.Core.RDD.RDD_Operator.K_V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * groupByKey可以自定义partitioner
  */
object groupByKey {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD: RDD[String] = sc.makeRDD(Array("word", "new", "scala", "new", "scala"))
    val mapRDD: RDD[(String, Int)] = listRDD.map((_, 1))
    /**
      * groupByKey的返回结果，value是一个迭代器
      * (new,CompactBuffer(1, 1))
      * (scala,CompactBuffer(1, 1))
      * (word,CompactBuffer(1))
      */

    val groupRDD: RDD[(String, Iterable[Int])] = mapRDD.groupByKey()
    groupRDD.collect().foreach(println)
    /**
      * 通过sum函数，计算迭代器的数量
      * (new,2)
      * (scala,2)
      * (word,1)
      */
    val res: RDD[(String, Int)] = groupRDD.map(x => (x._1, x._2.sum))
    res.collect().foreach(println)

  }
}
