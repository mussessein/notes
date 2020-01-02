package notes.collectionNotes

/**
 * Option：选项，可选的
 * 表示一个可能包含值的容器：
 * 所以其有两个子类：None，Some[T]
 * 1. Map的.get方法的返回值为Option，表示可能为空
 * 2. 既然可能为空，我们在获取Option的值的时候，就要用getOrElse传入一个默认值；
 */
object MyOption {
  def main(args: Array[String]): Unit = {
    val m = Map(
      1 -> "a",
      2 -> "b",
      3 -> "c",
      4 -> "d"
    )
    // 返回Option泛型类
    val first: Option[String] = m.get(1)
    // 获取Option
    val res = first.getOrElse("h")
    println(s"res=${res}")

    // option的模式匹配
    val result = first match {
      case Some(n) => n
      case None => "none"
    }
    println(s"result=${result}")

  }
}
