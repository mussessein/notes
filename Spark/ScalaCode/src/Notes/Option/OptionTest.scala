package Notes.Option

/**
 * scala不使用null,而是Option/Some/None（如果一个变量可能为Null，请定义为Option）
 * Option对象表示：此值是可选的，如果此值存在就是一个Some，不存在就是一个None
 * 比如说：
 * 我们定义一个方法，方法的返回值并不确定是否为None，就可以返回Option
 */
object OptionTest {

  // 此方法不确定返回值
  def test(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    } catch {
      case e: Exception => None
    }
  }

  def main(args: Array[String]): Unit = {
    val i1: Option[Int] = test("fool") // None
    val i2: Option[Int] = test("1") // Some(1)
  }
}
