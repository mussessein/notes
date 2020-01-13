package notes.collectionNotes.arrays

import scala.util.Sorting

/**
 * Array:
 * 1. 有序(非排序，顺序由索引确定，可以 arr(1)来索引)
 * 2. 可变(可以对任意元素重新复制)
 */
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
    numArray.foreach(println)
    //2.Range
    val array: Array[Range.Inclusive] = Array(1 to 10)
    array.foreach(println)
    //3.mkString
    val strArray: Array[String] = Array("Spark","Scala","Hadoop")
    println(strArray.mkString("---"))  //Spark---Scala---Hadoop
    println(strArray.mkString("<", "-", ">"))  //<Spark-Scala-Hadoop>

    //4.函数
    val nums = Array(1,7,3,2,5)
    nums.foreach(println)
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
