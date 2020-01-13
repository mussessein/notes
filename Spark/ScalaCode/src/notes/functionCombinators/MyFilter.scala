package notes.functionCombinators

object MyFilter {
  def main(args: Array[String]): Unit = {
    /**
     * 过滤Map
     *  namePhone._2把 Map当成Tuple
     */
    val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
    val filterRes: Map[String, Int] = extensions
      .filter((namePhone: (String, Int)) => namePhone._2 < 200)
    println(s"filterRes=${filterRes}")

    /**
     * filter搭配模式匹配
     */
    val filterMatch: Map[String, Int] = extensions.filter({
      case (name, extension) => extension < 200
    })
    println(s"filterMatch=${filterMatch}")

  }
}
