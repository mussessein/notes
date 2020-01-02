package notes.functionCombinators

/**
 * flatMap经常用于模式匹配
 */
object MyFlatMap {
  def main(args: Array[String]): Unit = {
    /**
     * flatmap与map的区别:返回类型
     */
    val data = List(1, 2, 3)
    val resMap = data.map(x => x match {
      case 1 => List(1)
      case _ => List(x * 2)
    })
    val resflatMap = data.flatMap(x => x match {
      case 1 => List(1)
      case _ => List(x * 2)
    })
    println(s"resMap=>$resMap")
    println(s"resflatMap=>$resflatMap")
//    resMap.foreach(println) // 返回:List(List(1),List(4),List(6))
//    resflatMap.foreach(println) // 返回List(1,4,6)

    /**
     * flatMap：扁平化，就是将结构化为一个层级
     * x=>x相当于flatten
     */
    val nums = List(List(1,2,3),List(7,6,5))
    val flatRes: List[Int] = nums.flatMap(x => x)
    println(s"flatRes = ${flatRes}")
    //List(1, 2, 3, 7, 6, 5)
  }
}
