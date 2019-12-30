package notes.Function.apply

/**
 * scala中函数最高有22个参数，单纯就是想设置为22
 *
 */
class AddOne extends Function1 [Int,Int]{
  override def apply(v1: Int): Int = v1+ 5
}
object AddOne{
  def main(args: Array[String]): Unit = {
    val addone = new AddOne
    val res:Int = addone(1)
    println(res)

  }
}