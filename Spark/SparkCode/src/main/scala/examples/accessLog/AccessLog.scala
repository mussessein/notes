package examples.accessLog

import scala.util.matching.Regex

/**
 * 根据数据的格式，创建case class
 * 9个字段
 */
case class AccessLog(
                      ipAddress: String,
                      clientIdented: String,
                      userId: String,
                      dataTime: String,
                      method: String,
                      endPoint: String,
                      protocol: String, // 协议tcp
                      responseCode: Int, // 响应状态码
                      contentSize: Long
                    )

/**
 * 伴生对象
 * 用来定义 静态方法
 */
object AccessLog {
  // 三引号表示字符串,正则：以^开始，$结束,每个字段放在一个()里面
  // 1.1.1.1 - - [21/Jul/2014:10:00:00 -0800] "GET /chapter1/java/src/main/java/com/databricks/apps/logs/LogAnalyzer.java HTTP/1.1" 200 1234
  val pattern: Regex = """^(\S+) (-|\S+) (-|\S+) \[([\w:/]+\s[+\-]\d{4})\] "(\S+) (\S+) (\S+)" (\d{3}) (\d+)""".r

  /**
   * 通过正则解析log文件，将每行数据解析成case class实例
   *
   * @param log
   * @return
   */
  def parseLogLine(log: String): AccessLog = {
    // 使用正则匹配每一行日志信息
    val res: Option[Regex.Match] = pattern.findFirstMatchIn(log)
    if (res.isEmpty) {
      throw new RuntimeException(s"Cannot parse log line:${log}")
    }

    val matchRes: Regex.Match = res.get
    // 返回值为 AccessLog对象
    AccessLog(
      matchRes.group(1),
      matchRes.group(2),
      matchRes.group(3),
      matchRes.group(4),
      matchRes.group(5),
      matchRes.group(6),
      matchRes.group(7),
      matchRes.group(8).toInt,
      matchRes.group(9).toLong
    )
  }

  /**
   * 过滤脏数据：通过filter算子过滤
   * 符合正则的，返回true
   * 不符合，返回fasle
   */
  def isValidateLogLine(log: String): Boolean = {
    val res: Option[Regex.Match] = pattern.findFirstMatchIn(log)
    // 匹配成功
    !res.isEmpty
  }


}