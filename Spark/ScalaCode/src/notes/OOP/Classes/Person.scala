package notes.OOP.Classes

/**
 * 声明2个属性(默认是private)
 * 注意:
 * 1. class文件只会生成一个.class类字节码文件,object会生成两个字节码文件.class和$.class
 * 1. 声明的属性,必须显示初始化,底层是private
 * 2. scala自动生成两个public的getter和setter,不需要写这两个方法,可以直接.name修改
 */
class Person(
              var name: String,
              var age: Int
            ) {
  // some methods
  override def toString(): String = s"$name is $age years old"

}

object Person {
  /**
   * 1. 实现apply方法，不需要new
   * 可以通过重载多个apply方法，实现多个构造器
   * 2. object 下都为静态方法
   */
  def apply(name: String, age: Int): Person = {
    val p = new Person(name, age)
    p
  }

  // unapply方法：通过对象获得属性
  def unapply(p: Person): String = s"${p.name}, ${p.age}"

  def test(): String = {
    "test"
  }

}