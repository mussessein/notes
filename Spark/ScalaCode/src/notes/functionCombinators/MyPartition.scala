package notes.functionCombinators

/**
 * 分割列表
 */
object MyPartition {
  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val res: (List[Int], List[Int]) = numbers.partition(_ % 2 == 0)
    println(res)
  }
}
