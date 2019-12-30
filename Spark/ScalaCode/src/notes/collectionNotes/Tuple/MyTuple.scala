package notes.collectionNotes.Tuple

/**
 * tuple
 * 1. 任意类型的简单集和：可以存放不同类型
 * 2. 基于下标进行访问 tuple._1
 * 3. 下标起始于 1 而非 0
 * 4. 两个元素的tuple 可以使用语法糖 1->2
 */
object MyTuple {

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

    val tuple2 = 1->2
    println(tuple2)
  }
}
