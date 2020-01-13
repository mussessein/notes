package notes.functionCombinators

/**
 * 两个列表内容聚合到一个对偶列表中
 */
object MyZip {
  private val l1 = List(1, 2, 3)
  private val l2 = List("a", "b", "c")

  def main(args: Array[String]): Unit = {
    val res: List[(Int, String)] = l1.zip(l2)
    println(res)
    //List((1,a), (2,b), (3,c))
  }
}
