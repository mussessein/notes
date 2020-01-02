package sparkCore.RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

/**
  * 自定义一个分区器，类比HashPartitioner
  *
  */
object my_partitioner {

  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD_1: RDD[(Int, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc"), (4, "ddd"), (5, "eee")))
    val myRDD: RDD[(Int, String)] = listRDD_1.partitionBy(new my_partitioner(2))
    myRDD.saveAsTextFile("output")
  }
}

/**
  * 继承Partitioner抽象类
  * 需要实现两个抽象方法
  *
  * @param partitions
  */
class my_partitioner(partitions: Int) extends Partitioner {
  override def numPartitions: Int = {
    partitions
  }

  override def getPartition(key: Any): Int = key match {
    case null => 0
    case _ => 1 // _ 表示其余全部
  }
}
