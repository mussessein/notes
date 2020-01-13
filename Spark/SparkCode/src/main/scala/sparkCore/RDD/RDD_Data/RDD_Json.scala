package sparkCore.RDD.RDD_Data

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON

/**
  * 读取json文件：
  * 这里json文件必须是一行一数据
  */
object RDD_Json {
  def main(args: Array[String]): Unit = {
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)
    val dataJSON = sc.textFile("input/user.json")
    val res = dataJSON.map(JSON.parseFull)
    res.foreach(println)

    sc.stop()

  }
}
