package Notes.PatternMatching

/**
 * 6. 类型模式
 */
object Match6 {
  class A
  class B extends A
  class C extends A

  def matchClass(x:AnyRef)=x match {
    case x:String =>println("字符串")
    case x:B=>println("类B")
    case x:C=>println("类C")
    case x:A=>println("类A") // 子类可以被匹配为父类
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
