package Notes.Collection.Array

import scala.util.Sorting

object MyArray {

  def main(args: Array[String]): Unit = {
    /**
      * Array：定长数组
      * 数值类型初始化为0
      * 非数值类型初始化为null
      */
    //1.赋值
    val numArray: Array[Int] = new Array[Int](10)
    numArray(0)=10
    //2.Range
    val array: Array[Range.Inclusive] = Array(1 to 10)
    array.foreach(println)
    //3.mkString
    val strArray: Array[String] = Array("Spark","Scala","Hadoop")
    println(strArray.mkString("---"))  //Spark---Scala---Hadoop
    println(strArray.mkString("<", "-", ">"))  //<Spark-Scala-Hadoop>

    //4.函数
    val nums = Array(1,1,3,4,5)
    println(nums.sum)
    nums.min
    nums.max
    Sorting.quickSort(nums) // 排序
    println(nums.count(_ > 3))
    nums.map(_*2).filter(_>6)  // map-filter  (8,10)
    nums.distinct  // 去重(1,3,4,5)

    // 5.多维数组
    val dimArray: Array[Array[Int]] = Array(Array(1,2,3,4),Array(5,6,7,8))
  }
}
