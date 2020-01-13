package notes.functionCombinators

/**
 * fold:二元算子
 * 需要一个初始值(可以理解为上下文)
 */
object MyFold {
  def main(args: Array[String]): Unit = {
    // 简单例子
    val list = List(1, 2, 3, 4, 5)
    val res: Int = list.fold(1)(_ + _) // res=1+15
    // 复杂例子

  }


}
