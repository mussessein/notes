package notes.OOP.Classes

// the primary constructor 主构造函数,可以设置默认值
class Pizza(var crustSize: Int, var crustType: String) {

  /**
   * 三个辅助构造函数
   */
  def this(crustSize: Int) {
    this(crustSize, "THIN")
  }

  def this(crustType: String) {
    this(12, crustType)
  }

  def this() {
    this(12, "THIN")
  }

  override def toString = s"A $crustSize inch pizza with a $crustType crust"

}