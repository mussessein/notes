package notes.ControlProcess

object MyFor {
  def main(args: Array[String]): Unit = {
    // 1
    for (i <- 1 to 3) {
      print(i + " ")
    }

    /**
     * 2.until 左闭右开,可以设置步长
     * 可以逆序
     */
    for (i <- 0 until(10, 2)) {
      print(i)
    }
    for (i <- (0 until(5, 2)).reverse) {
      println(s"s=$i")
    }
    // 3.循环守卫(过滤条件)
    for (i <- 1 until 10 if i != 2; if i != 3) {
      print(i)
    }
    // 4.多变量
    for (i <- 1 until 5; j = i * i) {
      print(2 * i + j)
    }
    for (i <- 1 to 5; j <- i to 5) {
      print(i, j)
    }
    // 5.yield:返回一个集合
    val res = for (i <- 1 to 10) yield (i, i * i)
    println(res)

    // 6.range:左闭右开，步长为2
    for (i <- Range(1, 10, 2)) {
      println(i)
    }

  }
}
