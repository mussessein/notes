package notes.OOP.generic

class CacheTest(key:String,value:Int) extends Cache[String,Int]{
  override def delete(key: String): Unit = {}
  override def get(key: String): Int = value

  override def put(key: String, value: Int): Unit = {
  }
}

object CacheTest{
  private val test = new CacheTest("k1",1)
  test.get("k1")
}
