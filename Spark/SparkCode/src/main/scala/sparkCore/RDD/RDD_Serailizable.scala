package sparkCore.RDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 自行实现过滤器
  */
object RDD_Serailizable {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    val listRDD_1: RDD[String] = sc.makeRDD(Array("hadoop", "hive", "hbase", "Spark"))

    val search = new RDD_Serailizable("h")
    val res: RDD[String] = search.getMatch_1(listRDD_1)
    res.collect().foreach(println)
    /**
      * 调用getMatch不需要序列化
      * 跟对象没关系，传递的是字符串，字符串本身就能序列化
      */
  }
}

/**
  * 这里要想通信Executor，必须实现序列化
  *
  * @param query
  */
class RDD_Serailizable(query: String) extends java.io.Serializable {

  def isMatch(s: String): Boolean = {
    s.contains(query)
  }

  /**
    * 两种filter写法
    */
  def getMatch_1(rdd: RDD[String]): RDD[String] = {
    rdd.filter(isMatch)
  }

  def getMatch_2(rdd: RDD[String]): RDD[String] = {
    val str = query // 在Driver中执行
    rdd.filter(x => x.contains(str)) // 在Executor中执行
  }

}
