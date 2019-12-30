package notes.Function

/**
 * 值函数(也就是Lambda表达式)
 * 1.不能指定返回类型
 * 2.可以使用lazy懒加载
 * 3.值函数底层是被编译为一个类
 */
object ValFunction {
  def main(args: Array[String]): Unit = {

    val sum = (x: Int, y: Int) => x + y

    /**
     * 使用场景:
     * 1.作为高阶函数的输入(比如数组加1)
     */
    val array = Array(1, 2, 3, 4)
    // 加1函数
    val increment = (x: Int) => x + 1
    // 将函数传入高阶函数map
    val res: Array[Int] = array.map(increment)
    res.foreach(println)

    // 简化版:
    val result = array.map(x => x + 1)
    result.foreach(println)

  }
}
