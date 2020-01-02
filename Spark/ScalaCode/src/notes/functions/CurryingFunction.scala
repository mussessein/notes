package notes.functions

/**
 * 柯里化函数应用
 * 比如：一个函数有两个参数，相乘
 * 场景1：第二个参数已有，只需要第一个参数
 * 场景2：第一个参数已有，只需要第二个参数
 * 任何多参数的函数，都可以实现柯里化：
 * (num1:Int,num2:Int)===>(num1:Int) (num2:Int)
 */
object CurryingFunction {
  /**
   * 柯里化函数
   */
  def multiply(num1:Int) (num2:Int): Int ={
    num1 *num2
  }

  def main(args: Array[String]): Unit = {

    val multiplyOne = multiply(3) _
    val res = multiplyOne(2)
    print(res)
  }
}
