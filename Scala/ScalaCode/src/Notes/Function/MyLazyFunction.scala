package Notes.Function

/**
 * 惰性计算(懒加载)
 */
object MyLazyFunction {
  def sum(n1: Int, n2: Int): Int = {
    println("sum执行了")
    n1 + n2
  }

  def main(args: Array[String]): Unit = {
    lazy val res = sum(10, 30)
    println("===============")
    println(s"res=$res")
  }

  /**
   * 运行结果:说明在调用res之前,sum都没有执行
   * ===============
   * sum执行了
   * res=40
   **/
}
