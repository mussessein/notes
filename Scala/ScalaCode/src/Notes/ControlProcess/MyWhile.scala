package Notes.ControlProcess

object MyWhile {
  def main(args: Array[String]): Unit = {
    /**
     * while
     * 1. 同样是可以返回值
     */
    var a = 10

    /**
     * 2. scala中么有continue，break
     * 终止循环：
     * (1) boolean标志位
     * (2) return
     * (3) 使用Breadks对象的break方法
     */
    import scala.util.control.Breaks._
    // 模拟break
    breakable {
      for (i <- 1 to 5) {
        if (i > 2)
          break()
        println(s"i=$i")
      }
    }
    // 模拟continue
    while (a > 5) {
      breakable {
        if (a == 7) {
          break
        } else {
          println(a)
        }
      }
      a -= 1
    }
  }
}
