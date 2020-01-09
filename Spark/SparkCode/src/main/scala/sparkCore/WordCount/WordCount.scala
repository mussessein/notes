package sparkCore.WordCount

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Spark下scala写wordcount
 * local模式
 */
object WordCount {
  def main(args: Array[String]): Unit = {
    /**
     * SparkConf对象，配置spark框架的部署环境
     * master：ip，默认local
     * AppName：app id
     */
    val config: SparkConf = new SparkConf()
      /*.setMaster("spark://hadoop001:7077")*/
      .setMaster("local[*]")
      .setAppName("WordCount")
    /**
     * 1.通过SparkConf-创建Spark上下文对象sc
     * 2.外部创建RDD：testFile:读取文件
     * 3.flatMap:扁平化：分解每行String
     * 4.map:数据转化为tuple(key,value)形式
     * 5.reduceByKey:相同key聚合
     * 6.
     */
    val sc = new SparkContext(config)
    sc.setLogLevel("WARN")
    val lines: RDD[String] = sc.textFile("hdfs://hadoop001:9000/user/root/input/words.txt")
    val words: RDD[String] = lines.flatMap(line => line.split(" "))
    // lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)
    val result: Array[(String, Int)] = wordToSum.collect()

    //wordToOne.foreach(println)
    //wordToSum.foreach(println)
    result.foreach(println)
    println("=================降序排序======================")
    val wordDESC: RDD[(Int, String)] = {
      wordToSum.map(word => (word._2, word._1)).sortByKey(ascending = false)
    }
    wordDESC.collect().foreach(println)

    println("=================升序排序======================")
    val wordASC: RDD[(String, Int)] = wordToSum.sortBy(tuple => tuple._2, ascending = true)
    wordASC.collect().foreach(println)

    println("=================TOP排序======================")
    val wordTop: Array[(Int, String)] = wordToSum.map(word => (word._2, word._1)).top(3)
    wordTop.foreach(println)

    sc.stop()
  }
}
