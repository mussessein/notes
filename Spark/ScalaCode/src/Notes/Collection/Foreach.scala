package Notes.Collection

import scala.collection.mutable.ArrayBuffer

object Foreach {
  /**
   * 数组的遍历
   */
  def main(args: Array[String]): Unit = {
    val nums: ArrayBuffer[Int] = ArrayBuffer[Int]()
    nums ++= Array(1, 3, 4, 1, 6)

    // 1.mkString
    val str: String = nums.mkString("<", ",", ">")
    println(str)
    // 2. for
    for (i <- 0 to nums.length - 1) {
    }
    // 3. for-until：设置步长
    for (i <- 0 until(nums.length, 2)) {
      print(nums(i))
    }
    // 4. 倒序输出,until:左闭右开
    for (i <- (0 until nums.length).reverse) {
      print(nums(i))
    }
    // 5.直接数组遍历(推荐)
    for (elem <- nums) {
      print(elem)
    }
    // 6.遍历生成新的数组yield：带有返回值
    val array: ArrayBuffer[Int] = for (i <- nums) yield i * 2 // 2,6,8,2,12

    // yield处理数组，并返回新的数组
    val names = List("_adam", "_david", "_frank")
    val capNames = for (name <- names) yield {
      val nameWithoutUnderscore = name.drop(1)
      val capName = nameWithoutUnderscore.capitalize // 首字母大写
      capName
    }
    // 简化：
    val capitalNames = for (name <- names) yield {
      name.drop(1).capitalize
    }
    capitalNames.foreach(println)
  }
}
