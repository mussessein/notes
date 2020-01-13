package sparkCore.RDD.RDD_Operator.K_V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * reduce算子，求总和
 */
object RDD_reduce {
  def main(args: Array[String]): Unit = {
    /**
     * 创建spark上下文对象
     */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)
    val lines: RDD[String] = sc.textFile("input/word.txt")
    lines.foreach(println)
    val linelength: RDD[Int] = lines.map(line=>line.length)
    linelength.foreach(println)
    val totallength: Int = linelength.reduce((a,b)=>a+b)  // 任意两行不重复，相加，即求总和
    println(totallength)
    // linelength.persist()
  }
}
