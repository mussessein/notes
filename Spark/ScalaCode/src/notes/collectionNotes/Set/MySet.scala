package notes.collectionNotes.Set

/**
 * set
 * 1. 无序
 * 2. 不包含重复项
 */
object MySet {
  def main(args: Array[String]): Unit = {
    /**
     * 默认是：不可变set
     */
    val set1 = Set[Int]()


    /**
     * 可变set
     */
    val set = scala.collection.mutable.Set[Int]()
    // 添加
    set += 1
    set += 2 += 3
    set ++= Vector(4, 5)
    set.add(6)
    // 删除delete
    set -= 1
    set --= Array(4, 5)
    set.remove(2)
    set.clear() // 清空set
  }
}
