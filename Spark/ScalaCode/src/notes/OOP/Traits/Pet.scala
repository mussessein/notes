package notes.OOP.Traits

/**
 * 相当于java接口
 * 1. 用extends继承接口
 * 2. 多个traits使用with
 * 3. 可以在创建对象的时候，混入traits（见test）
 * 4. trait不存在构造函数，不能带有属性字段，如果必须要带有，可以使用抽象方法
 */
trait Pet {
  val variety:String
  def speak {
    println(variety+":Yo")
  } // concrete implementation of a speak method
  def comeToMaster(): Unit // abstract
}
