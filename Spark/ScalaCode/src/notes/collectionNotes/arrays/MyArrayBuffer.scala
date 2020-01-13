package notes.collectionNotes.arrays


object MyArrayBuffer {
  /**
   * 变长数组：ArrayBuffer
   * 需要引入scala.collection.mutable.ArrayBuffer
   */
  def main(args: Array[String]): Unit = {
    val strBuffer = scala.collection.mutable.ArrayBuffer[String]()
    // += 尾部添加元素
    strBuffer += "Spark"
    strBuffer += ("Hadoop", "Scala")
    // ++= 后跟任何集合
    strBuffer ++= Array("HBase", "Hive")
    strBuffer ++= List("Kafka", "Storm")
    // 插入
    strBuffer.insert(2, "Flume") // 指定位置插入元素
    strBuffer.insert(2, "Zookeeper", "Pig") // 指定位置插入多个
    // 删除
    strBuffer -= "Flume" // 删除指定元素
    strBuffer --= Array("Hadoop", "Storm") // 删除多个元素
    strBuffer.remove(1) // 删除指定元素索引
    strBuffer.remove(0, 3) // 从0开始，删除3个元素
    strBuffer.trimEnd(2) // 删除末尾2个元素
    strBuffer.trimStart(1) // 删除开头1元素
    // 定长，变长，转换
    val array: Array[String] = strBuffer.toArray
    val buffer = array.toBuffer


  }
}
