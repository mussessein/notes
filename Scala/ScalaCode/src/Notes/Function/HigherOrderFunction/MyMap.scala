package Notes.Function.HigherOrderFunction

/**
 * 高阶函数:可以操作其他函数的函数,即函数可以作为参数传入的函数
 * 比如map函数内可以传入一个lambda函数
 */
object MyMap {
  def main(args: Array[String]): Unit = {
    /**
     * map操作List
     */
    val array = List(1, 2, 3, 4).map(x => x + 1)
    // 当x在=>右边,只出现一次,可以简化:
    val res = List("Spark", "Hadoop", "Scala").map(_ * 2)

    /**
     * map操作KV
     */
    val list: List[String] = List("Sparl" -> 1, "Hadoop" -> 2, "Scala" -> 3).map(_._1)

    /**
     * 搭配filter
     */
    val list1: List[Int] = List(1, 2, 3, 4, 5, 6).filter(_ % 2 == 0).map(_ + 10)
    list1.foreach(println)
  }
}
