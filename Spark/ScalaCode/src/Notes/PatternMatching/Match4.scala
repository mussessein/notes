package Notes.PatternMatching

/**
 * 4. 序列模式
 * 5. 元组模式
 * 模式匹配对应的序列,匹配到了,返回对应的值
 */
object Match4 {

  def matchArray(x:AnyRef)=x match{
    case Array(index1,index2)=>println(s"第一个元素:$index1 ,第二个元素:$index2")
    case Array(_,_,index3,_,_*)=>println(s"第三个元素:$index3")
    case _=>println("else")
  }

  def main(args: Array[String]): Unit = {
    val arr  =Array(7,9)
    val array = Array(1,2,3,4,5,6)
    val tuple1=(1,2,3,4)
    matchArray(arr)
    matchArray(array)
    matchArray(tuple1)  // 匹配元组
  }
}
