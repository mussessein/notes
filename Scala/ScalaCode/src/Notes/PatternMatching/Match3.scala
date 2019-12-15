package Notes.PatternMatching
/**
 * 3. 构造函数
 */
object Match3 {
  case class Dog(val name:String,val age:Int)
  def matchDog(x:Dog)=x match {
    case Dog(_,10)=>"10岁的dog"
    case Dog("tedy",_)=>"名为tedy的dog"
    case _=>"else"
  }
  def main(args: Array[String]): Unit = {
    val dog1 = new Dog("gdx",10)
    val dog2 = new Dog("judy",11)
    val dog3 = new Dog("tedy",13)
    val dogs = List(dog1,dog2,dog3)
    dogs.foreach(dog=>println(matchDog(dog)))
  }
}
