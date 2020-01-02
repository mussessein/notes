package sparkCore.RDD.RDD_Operator.K_V_Operator

import org.apache.spark.{SparkConf, SparkContext}

object RDD_reduceByKey {
  /**
   * 创建spark上下文对象
   */
  val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
  val sc = new SparkContext(config)
}
