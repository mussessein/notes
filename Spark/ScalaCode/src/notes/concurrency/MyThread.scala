package notes.concurrency

class MyThread(s:String) extends Runnable{
  override def run(): Unit = {
    println(s)
  }
}
object MyThread{
  def main(args: Array[String]): Unit = {
    new MyThread("sssssssss").run()
  }
}
