package Notes.Collection

/**
 * 参考官方文档：collections-methods
 * https://docs.scala-lang.org/overviews/scala-book/collections-methods.html
 */
object CollectionMethod {
  def main(args: Array[String]): Unit = {

    val nums: List[Int] = (1 to 10).toList
    // map
    val mapNums: List[Int] = nums.map(_ * 2)
    mapNums.foreach(println)

    // filter
    val filterNums: List[Int] = nums.filter(_ < 4)
    filterNums.foreach(println)

    // foldLeft
    val res: Int = nums.foldLeft(100)(_ + _)
    val sum: Int = nums.sum
    println(s"res=$res sum=$sum")

    // head:取第一个元素
    val headChar = "das".head
    val numHead: Int = nums.head // 1

    // tail
    val numsTail: List[Int] = nums.tail // 2,3...10

    // take:取前n个元素
    // takeWhile
    val ints: List[Int] = nums.take(3)
    // drop
    // dropWhile
    // find
    //reduce


  }
}
