package notes.Exception

/**
 * 异常的捕获:
 * (1)throw
 * (2)try...catch
 * 如果不捕获异常,代码中断
 * 捕获异常,代码能继续执行
 */
object MyTryCatch {
  /**
   * try...catch
   * 1.只能写一个catch,可以进行模式匹配
   */
  def main(args: Array[String]): Unit = {
    try {
      val t = 10 / 0
    } catch {
      case ex: ArithmeticException => {
        println("算数异常")
      }
      case _: Exception => {
        println("其他异常")
      }
    } finally {
      println("finall代码")
    }
  }

  /**
   * throw
   * (1)函数内抛出
   * (2)注解抛出
   */

  def test1(n: Int): Unit = {
    if (n > 5) {
      throw new ArithmeticException("You are not eligible")
    }
  }

  @throws(classOf[NumberFormatException])
  def test2(s: String) = {
    s.toInt
  }
}
