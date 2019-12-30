package notes.PatternMatching

/**
 * 7. 变量绑定模式
 */
object Match7 {
  case class Cat(val name:String,val age:Int)

  def matchVariable(x:AnyRef)=x match{
    case a@Cat(_,4)=>println("匹配到4,赋值给c:"+a)
    case b@Cat(s,_) if (s=="niu")=>println("匹配到niu,赋值给c:"+b)
    case c@Cat("ketty",_)=>println("匹配到ketty,赋值给c:"+c)
    case _=>"else"
  }
  def main(args: Array[String]): Unit = {
    val cat1 = new Cat("pi",4)
    val cat2 = new Cat("niu",10)
    val cat3 = new Cat("ketty",8)
    val cats: Array[Cat] = Array(cat1,cat2,cat3)
    cats.foreach{matchVariable}
  }
}
