package sparkCore.Accumulator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 使用自定义累加器
  * 返回给Driver含有"H"的单词
  */
object Word_Accumulator {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("myAccumulator")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[String] = sc.makeRDD(List("Hadoop", "Hive", "Hbase", "Spark", "Scala"), 2)

    /**
      * 创建自定义累加器
      * 注册累加器
      */
    val my_Accumulator = new My_Accumulator
    sc.register(my_Accumulator)
    rdd.foreach {
      word => {
        my_Accumulator.add(word)
      }
    }
    println(my_Accumulator.value)


    sc.stop()
  }
}
