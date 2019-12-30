package notes.collectionNotes.Map


object MyMap {
  def main(args: Array[String]): Unit = {
    // immutable Map
    val m = Map(
      1 -> "a",
      2 -> "b",
      3 -> "c",
      4 -> "d"
    )
    val keys: Iterable[Int] = m.keys
    val values: Iterable[String] = m.values
    val bool: Boolean = m.contains(3)
    val upperMap: Map[Int, String] = m.transform((k, v) => v.toUpperCase)
    /**
     * 可变scala.collection.mutable.Map
     */
    val states = scala.collection.mutable.Map(
      "AK" -> "Alaska",
      "IL" -> "Illinois",
      "KY" -> "Kentucky"
    )
    // 添加
    states += ("AL" -> "Alabama")
    states ++= Map("CA" -> "California", "CO" -> "Colorado")
    // 删除：只要指定key
    states -= "AR"
    states -= ("AL", "AZ")
    states --= List("AL", "AZ")
    // 更新
    states("AK") = "Alaska, A Really Big State"

    /**
     * 遍历：Traversing
     * 1. for循环遍历
     * 2. 模式匹配遍历
     */
    for ((k, v) <- states) println(s"key: $k, value: $v;")
    states.foreach {
      case (k, v) => println(s"key: $k, value: $v;")
    }
  }
}
