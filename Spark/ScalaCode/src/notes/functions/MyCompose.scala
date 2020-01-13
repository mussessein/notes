package notes.functions

/**
 * 函数组合：组合两个函数，形成一个新的函数
 * 1. compose: f(g(x))
 * 2. andThen: g(f(x))
 */
object MyCompose {
  def main(args: Array[String]): Unit = {
    // 定义两个函数
    def f(s:String) = s"f--->${s}"
    def g(s:String) = s"g--->${s}"
    val fCompose = f _ compose g _
    val res: String = fCompose("cccccccccc")
    println(res)

    val fAndThen = f _ andThen g _
    val result: String = fAndThen("cccccccccc")
    println(result)
  }
}
