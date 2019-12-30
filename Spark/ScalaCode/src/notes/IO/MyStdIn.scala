package notes.IO
import scala.io.StdIn.readLine

/**
 * 输入
 */
object MyStdIn extends scala.AnyRef {
  def main(args: Array[String]): Unit = {
    print("Enter your first name: ")
    val firstName = readLine()

    print("Enter your last name: ")
    val lastName = readLine()

    println(s"Your name is $firstName $lastName")
  }
}
