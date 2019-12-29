package Notes.OOP.AbstractClass

abstract class Pet(name: String) {
  def speak(): Unit = println("Yo") // concrete implementation
  def comeToMaster(): Unit // abstract method
}
