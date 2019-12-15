package Notes.Function.HigherOrderFunction

/**
 * reduce是一个二元操作算子,两个参数
 */
object MyReduce {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3, 4, 5)
    val sum = list.reduce(_ + _) // 求和 sum=15
    /**
     * 操作的方向不同,分为:
     * reduceLeft(默认方向) 和 reduceRight
     */
    val res: Int = list.reduceRight((x: Int, y: Int) => {
      println(x, y)
      x + y
    })
    //println(res)
  }
}
