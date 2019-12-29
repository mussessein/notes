package Notes.OOP.Traits

object TraitsTest {

  class Dog(name: String)

  /**
   * 在创建对象的时候，混入traits
   */
  def main(args: Array[String]): Unit = {
    val kim = new Dog("kim") with Pet {
      override def comeToMaster(): Unit = {
        print("come")
      }
    }

  }
}
