package notes.collectionNotes.List

import scala.collection.mutable.ListBuffer

object MyListBuffer {
  def main(args: Array[String]): Unit = {
    /**
     * 可变数组ListBuffer
     * 可以直接插入删除
     */
    val buffer: ListBuffer[Double] = ListBuffer(1, 2, 3, 4.5)
    buffer(1) = 2
  }
}
