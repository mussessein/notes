package Notes.Collection.Tuple

object TupleDemp {

  class Person(var name: String)

  /**
   * tuple存放不同类型的一种结构
   * tuple不是集合类！不能使用集合方法，只是一个小容器
   */
  def main(args: Array[String]): Unit = {
    val tuple1 = (3, "Three", new Person("David"))
    // 访问tuple元素
    val t1: Int = tuple1._1
    val (x, y, z) = tuple1
  }
}
