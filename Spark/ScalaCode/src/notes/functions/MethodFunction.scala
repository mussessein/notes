package notes.functions

/**
 * scala的方法method和函数function，底层一样一样
 * 方法是依赖于对象的
 * 函数不依赖对象
 */
object MethodFunction {

  class test {
    def sum(n1: Int, n2: Int): Int = {
      n1 + n2
    }
  }

  def main(args: Array[String]): Unit = {
    /**
     * 方法m 转 函数f
     */
    val m = new test
    println(m.sum(10, 20))
    // 函数
    val f1 = m.sum _
    println(f1)
    println(f1(10, 20))

    /**
     * 直接定义函数
     */
    val f2 = (n1: Int, n2: Int) => {
      n1 + n2
    }
    println(f2)
    println(f2(10, 1))
  }
}

