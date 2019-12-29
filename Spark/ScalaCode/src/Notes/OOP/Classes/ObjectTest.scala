package Notes.OOP.Classes

/**
 * object类型对象,默认是单例
 * 1. 底层生成public final class SingleObject$
 * 2. 并且类内生成一个静态实例对象:public static final SingleObject$ MODULE$
 */
object ObjectTest {
  var sutno: Int = _

  def main(args: Array[String]): Unit = {
    val s1 = ObjectTest
    val s2 = ObjectTest
    s2.sutno = 2
    println(s1.sutno)
    println(s2.sutno)

    // Person,这里不需要new，是应为Object类实现了apply方法
    val Kim = Person("Kim", 12)
    // 静态方法
    val str: String = Person.test()


  }
}
