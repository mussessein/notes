package sparkCore.RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD_Make {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    /**
      * 1. 第一种，从集合中创建RDD
      * 从内存中创建RDD：makeRDD \ parallelize
      * makeRDD底层调用了parallelize
      */
    val listRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 5))
    listRDD.collect().foreach(println)
    val arrayRDD: RDD[Int] = sc.parallelize(Array(1, 2, 3, 4, 5),2)
    arrayRDD.collect().foreach(println)
    /**
      * 2. 从外部存储系统中创建RDD：
      * 比如：WordCount中读取文件textFile()
      * 11个数据,2个分区，最终会生成3各分区:5,5,1
      */
    val fileRDD: RDD[String] = sc.textFile("input", 2);
     //将RDD持久化
    fileRDD.saveAsTextFile("output");

  }

}
