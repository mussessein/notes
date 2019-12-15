package Notes.Collection.Vector

object MyVector {

  case class Person(name: String)

  /**
   * vector是不可变数据结构
   *
   */
  def main(args: Array[String]): Unit = {
    // vector创建
    val nums = Vector(1, 2, 3, 4, 5)
    val strings = Vector("one", "two")
    val peeps = Vector(
      Person("Bert"),
      Person("Ernie"),
      Person("Grover")
    )

    // 添加
    val v2 = nums :+ 4
    val v3 = nums ++ Vector(4, 5)

  }
}
