package Spark.Core.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_Filter {
  def main(args: Array[String]): Unit = {
    /**
     * 创建spark上下文对象
     */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val list: List[(String, Int)] = List(("a",1),("b",2),("c",3),("d",5))
    val RDDTest: RDD[(String, Int)] = sc.makeRDD(list)
    val res: RDD[(String, Int)] = RDDTest.filter(_._2 > 2)
    res.collect().foreach(println)
  }
}
