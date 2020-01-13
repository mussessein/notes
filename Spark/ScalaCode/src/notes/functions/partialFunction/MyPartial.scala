package notes.functions.partialFunction

/**
 * 偏函数：
 * 函数：(Int) => String 可以接受所有的Int类型的值
 * 偏函数：(Int) => String 只能接受部分Int类型的值
 *
 */
object MyPartial {
  def main(args: Array[String]): Unit = {
    val one: PartialFunction[Int, String] = { case 1 => "one" }
    /**
     * isDefinedAt:判断是否能被case处理
     */
    val bool1: Boolean = one.isDefinedAt(1) // true
    val bool2: Boolean = one.isDefinedAt(2) // false
  }
}
