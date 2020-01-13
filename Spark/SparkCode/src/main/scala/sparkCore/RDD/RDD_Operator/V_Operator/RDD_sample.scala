package sparkCore.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 抽样算子：sample
  * 从指定数据集中进行抽象
  * 数据倾斜
  */
object RDD_sample {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)

    // 创建RDD，分成3个分区
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10)

    /**
      * 参数：
      * withReplacement(是否放回数据):true泊松/false伯努利
      * fraction：必须在[0,1]之间，它是一个分数,表示抽取的比例
      * seed：随机数种子，相同seed随机结果一样
      */
    val sampleRDD: RDD[Int] = listRDD.sample(true, 0.4)
    sampleRDD.collect().foreach(println)

  }
}
