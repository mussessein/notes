package notes.Function

/**
 * 函数写法:
 * (1).确定返回值
 * def 函数名 [参数:参数类型.....]:[返回值类型]={ 函数体 }
 * (2).不确定返回值,类型推导完成(或者声明为Any类型)
 * def 函数名 [参数:参数类型.....]={ 函数体 }
 * (3).没有返回值(称之为过程)
 * def 函数名 [参数:参数类型.....]:Unit={ 函数体 }
 * def 函数名 [参数:参数类型.....]{ 函数体 }
 */
object MyFunction {
  /**
   * 注意:
   * 1.如果函数内明确使用return,就不能使用类型推导,需要明确返回类型
   * 2.参数列表可以带有默认值,如果多个参数,默认从左到右覆盖(可以使用带名参数)
   * 3.递归函数必须声明返回值
   * 4.可变参数:必须在参数列表的最后
   */
  def sayHello(word: String = "hello"): Unit = {
    println(word)
  }

  // 多默认值参数
  def connect(add: String = "localhost", port: String = "3306", user: String = "root", passwd: String): Unit = {
    println(add)
    println(port)
    println(user)
    println(passwd)
  }

  // 可变参数
  def sum(args: Int*): Int = {
    var sum = 0
    for (i <- args) {
      sum += i
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    // 带名参数
    connect(user = "hadoop", passwd = "123456")
  }
}
