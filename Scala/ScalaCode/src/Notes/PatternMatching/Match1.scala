package Notes.PatternMatching

/**
 * 七大模式匹配类型
 * 1. 常量类型
 * 2. 变量类型
 * 3. 构造函数
 * 4. 序列模式
 * 5. 元组模式
 * 6. 类型模式
 * 7. 变量绑定模式
 */
object Match1 {
  def main(args: Array[String]): Unit = {
    for (i <- 1 to 5) {
      i match {
        case 1 => println(1)
        case 2 | 3 => println(2)
        case _ => println("else")
      }
    }

    for (i <- 1 to 5) {
      i match {
        case a if 0 to 3 contains a => println("0-3 range: " + a)
        case b if 3 to 5 contains b => println("3-5 range: " + b)
        case _ => println("Hmmm...")
      }
    }

  }
}
