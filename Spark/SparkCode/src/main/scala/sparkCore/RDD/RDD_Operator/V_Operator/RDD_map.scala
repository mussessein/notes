package sparkCore.RDD.RDD_Operator.V_Operator

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * 这里介绍了算子：
  * map，mapPrtitions，mapPartitionsWithIndex，flatMap
  */
object RDD_map {
  def main(args: Array[String]): Unit = {
    /**
      * 创建spark上下文对象
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(config)
    /**
      * 创建RDD，参数：seq（数据），numSlices（分区）
      * 10个数，放进2个分区;11个数，也不会增加分区，多余的数放在最后一个分区
      */
    val listRDD: RDD[Int] = sc.makeRDD(1 to 10, 2)

    /**
      * map算子：以数据为单位
      * 效率略低
      * 是以数据为单位进行处理的，需要处理10次，产生新的RDD
      */
    val res_1: RDD[Int] = listRDD.map(_ * 2)

    /**
      * mapPartitions算子:以分区为单位
      * 比map算子效率高
      * 一个函数处理一个分区(data)，处理速度高于map
      * 但是，会遗留数据引用，有可能OOM
      */
    var res_2: RDD[Int] = listRDD.mapPartitions(datas => {
      datas.map(data => data * 2)
    })

    /**
      * mapPartitionsWithIndex算子：以分区索引为单位
      * 将每个分区，定义一个索引
      *
      */
    var res_3: RDD[(Int, String)] = listRDD.mapPartitionsWithIndex {
      case (num, datas) => {
        datas.map((_, ",分区号:" + num))
      }
    }

    /**
      * flatMap
      * 这里类型发生转变,将list中的元素单独取出了
      * 相当于wordcount中，将每行的单词，单独取出
      */
    val RDDlist: RDD[List[Int]] = sc.makeRDD(Array(List(1, 2), List(3, 4)))
    val res_flatMap: RDD[Int] = RDDlist.flatMap(datas => datas)
    res_flatMap.collect().foreach(println)


  }

}
