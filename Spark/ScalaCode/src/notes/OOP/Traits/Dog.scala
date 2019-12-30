package notes.OOP.Traits

class Dog extends Pet {
  override val variety: String = "Teddy"
  override def comeToMaster(): Unit = {
    print("come")
  }
}
