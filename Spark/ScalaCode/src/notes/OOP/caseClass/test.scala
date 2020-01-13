package notes.OOP.caseClass

object test {
  def main(args: Array[String]): Unit = {
    val cat1 = Cat
    val cat2 = Cat("Kit",5)
    val str = cat2.toString()
    val flag = cat1.equals(cat2)
    println(str +"\n"+ flag)
  }
}
