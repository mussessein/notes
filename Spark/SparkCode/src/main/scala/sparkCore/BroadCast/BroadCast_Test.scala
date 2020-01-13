package sparkCore.BroadCast

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 广播变量
  */
object BroadCast_Test {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("BroadCast")
    val sc = new SparkContext(sparkConf)

    val rdd: RDD[(Int, String)] = sc.makeRDD(List((1, "a"), (2, "b"), (3, "c")))

    /**
      * 创建自定义累加器
      * 注册累加器
      */
    val list = List((1, 1), (2, 2), (3, 3))
    val broadcast: Broadcast[List[(Int, Int)]] = sc.broadcast(list)
    val mapRDD: RDD[(Int, (String, Any))] = rdd.map {
      case (key, value) => {
        var v2: Any = null
        for (t <- broadcast.value) {
          if (key == t._1) {
            v2 = t._2
          }
        }
        (key, (value, v2))
      }
    }
    mapRDD.collect().foreach(println)
    sc.stop()
  }
}
