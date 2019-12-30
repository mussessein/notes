package notes.collectionNotes.List

/**
 * List列表
 * 分为不可变List，可变ListBuffer
 * List：
 * 1. 有序(非排序，顺序由索引确定)
 * 2. 不可变:list2(1)=10将报错
 */
object MyList {
  def main(args: Array[String]): Unit = {
    /**
     * 不可变数组List
     * 1. 操作数组只能返回出新的数组
     * 2. 无法插入，删除
     */

    // 创建List：
    val numsList: List[Int] = (1 to 10).toList
    val strList: List[String] = List("Spark", "Scala", "Hbase")
    val list = 1 :: 2 :: 3 :: Nil
    val nums = List(1, 2, 3) ::: List(5, 6, 7)
    val list1: List[Int] = List.range(0, 3) // (0,1,2)
    val list2: List[Int] = List.range(0, 5, 2) // 步长2,(0,2,4)
    //list2(1)=10
    // 添加元素
    val list4: List[Int] = list :+ 4
    val list5: List[Int] = list ++ List(7, 8)

    // List method
    list.isEmpty
    list.head // 取第一个元素
    list.tail // 取除了第一个外,剩余元素的列表
    list.last // 取最后一个元素
    list.init // 除最后一个元素外,返回一个List
    list.reverse
    val tuple: (List[Int], List[Int]) = nums.splitAt(2) // 分割((1,2),(3,5,6,7))
    val list3: List[Int] = List.concat(List(1, 2), List(3, 4)) // concat连接

    // 删除
    val n = nums drop 4 // 丢弃前4个元素,返回n(6,7)
    nums.drop(4)
    val m = nums take 2 // 获取前两个元素,m(1,2)
    nums.take(2)

    // 转数组
    val array: Array[Int] = nums.toArray

  }
}
