package notes.PatternMatching
/**
 * 2. 变量类型
 */
object Match2 {
  /**
   * 返回值自动判定类型
   * 若有多个类型,判定为Any
   */
  def matchTest(x:Int)=x match {
    // guard
    case i if i%2==0=>s"$x 能被2整除"
    case i if i%3==0=>s"$x 能被3整除"
    case _ =>0  // 必须放在最后,否则后续代码无法执行
  }

  def main(args: Array[String]): Unit = {

    for (i<-0 to 5){
      i match {
        case 1=>println(i)
        case x if (x%2==0)=>println(s"$x 为偶数")
        case _=>println("else")
      }
    }

    var res=matchTest(3)
    println(res)
  }
}
