package notes.collectionNotes

/**
 * 参考官方文档：collections-methods
 * https://docs.scala-lang.org/overviews/scala-book/collections-methods.html
 */
object CollectionMethod {
  def main(args: Array[String]): Unit = {

    val nums: List[Int] = (1 to 10).toList
    println(s"nums=$nums")
    // map
    val mapNums: List[Int] = nums.map(_ * 2)
    println(s"mapNums=$mapNums")

    // filter
    val filterNums: List[Int] = nums.filter(_ < 4)
    println(s"filterNums=$filterNums")

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
    val takeNums: List[Int] = nums.take(3)
    println(s"takeNums=$takeNums")

    // takeWhile

    // drop：删除前i个元素,返回剩余
    val dropNums: List[Int] = nums.drop(1)
    println(s"dropNums=$dropNums")

    // dropWhile：删除满足条件的第一个元素,只会判断第一个元素
    val dropWhileNums: List[Int] = nums.dropWhile(x => x % 2!=0)
    println(s"dropWhileNums=$dropWhileNums")

    // find:返回第一个匹配值
    val findNums: Option[Int] = nums.find(x => x % 2 == 0)
    val findValue: Int = findNums.getOrElse(0)
    println(s"findValue=$findValue")

    //reduce


  }
}
