package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 按照传入的函数的返回值进行分区
  */
object RDD_groupBy {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))
    /**
      * 分组
      * 形成一个元祖（key-v）：v是一个数据集合
      * 下面例子：以余数作为key，1-5:中，余数0的为一个key，余数1的为一个key
      */
    val res: RDD[(Int, Iterable[Int])] = listRDD.groupBy(i => i % 2)

    res.collect().foreach(println)

    /**
      * 结果：
      * (0,CompactBuffer(2, 4))
      * (1,CompactBuffer(1, 3, 5))
      */
  }
}
