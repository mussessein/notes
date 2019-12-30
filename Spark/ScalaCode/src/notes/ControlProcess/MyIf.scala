package notes.ControlProcess

import scala.util.control.Breaks

/**
 * 在scala中任意表达式，都具有返回值
 */
object MyIf {
  def main(args: Array[String]): Unit = {
    /**
     * if
     * 1.可以任意返回值，自动推断类型，也可以是Unit返回()
     * 2.scala没有三元运算符
     */

    var a = 8
    val b = if (a > 10) {
      12
    } else {
      "ss"
    }
    val c = true
    val d = if (c) 1 else 0


  }
}
