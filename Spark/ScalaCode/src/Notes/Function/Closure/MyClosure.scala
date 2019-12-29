package Notes.Function.Closure

/**
 * 闭包的简单实现:
 * 根据运行时数据,传入不同参数,返回不同结果的函数
 */
object MyClosure {

  def test(f1: Double => Double, f2: Double => Unit) = {
    val res = f1(10)
    f2(res)
  }

  def main(args: Array[String]): Unit = {
    val f1 = (x: Double) => x * 2
    val f2 = (x: Double) => println(x)
    test(f1, f2)
  }
}
