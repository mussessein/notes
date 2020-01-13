package notes.PatternMatching

/**
 * 6. 类型模式
 * x:类型 =>
 * 自上而下，一次执行，匹配到停止
 */
object Match6 {
  class A
  class B extends A
  class C extends A

  def matchClass(x:AnyRef)=x match {
    case x:String =>println("字符串")
    case x:A=>println("类A")
    case x:B=>println("类B")
    case x:C=>println("类C")

    case _=>"else"
  }

  def main(args: Array[String]): Unit = {
    val a = new A
    val b =new B
    val c = new C
    val s:String ="new"
    matchClass(a)
    matchClass(b)
    matchClass(c)
    matchClass(s)
  }
}
