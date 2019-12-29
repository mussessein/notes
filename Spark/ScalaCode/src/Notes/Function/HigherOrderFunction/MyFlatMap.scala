package Notes.Function.HigherOrderFunction

/**
 * flatmap经常用于模式匹配
 */
object MyFlatMap {
  def main(args: Array[String]): Unit = {
    /**
     * flatmap与map的区别:返回类型
     */
    val list = List(1, 2, 3)
    val resMap = list.map(x => x match {
      case 1 => List(1)
      case _ => List(x * 2)
    })
    val resflatMap = list.flatMap(x => x match {
      case 1 => List(1)
      case _ => List(x * 2)
    })
    resMap.foreach(print) // 返回:List(List(1),List(4),List(6))
    resflatMap.foreach(print) // 返回List(1,4,6)
  }
}
