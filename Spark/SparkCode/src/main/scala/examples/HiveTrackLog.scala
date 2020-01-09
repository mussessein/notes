package examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object HiveTrackLog {
  /**
   * 如果Spark Application运行在本地的话，Driver Program
   * JVM Process
   */
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("TrackLog Analyzer Application")
    val sc = SparkContext.getOrCreate(sparkConf)

    // 读取hive表数据：trackRDD为一个分区的数据
    val trackRDD = sc.textFile("/user/hive/warehouse/db_track.db/yhd_log/date=20150828")
    // 测试
    println(s"Count = ${trackRDD.count()} \n ${trackRDD.first()}")


    /**
     * 分析数据，需求如下：
     * 统计每日的PV和UV
     * PV：页面访问量/浏览量
     * pv = COUNT(url)  url 不能为空  url.length > 0  第2列
     * UV：访客数
     * uv = COUNT(DISTINCT guid)                     第6列
     * 时间：
     * 用户访问网站的时间字段
     * trackTime -> 2015-08-28 18:10:00            第18列
     */
    // 清洗数据，提取字段
    val filteredRDD: RDD[(String, String, String)] = trackRDD
      // 防止空字符串，每一行
      .filter(line => line.trim.length > 0)
      // line为每一条记录
      .map(line => {
        // cols为一条记录的每个字段的数组
        val cols: Array[String] = line.split("\t")
        // return -> (trackTime, url, guid)  三元组
        (cols(17).substring(0, 10), cols(1), cols(5))
      })

    // RDD数据放到内存中，后面对RDD进行重复使用, Executor内存比较大情况下
    // cache 底层 persist(MEMORY_ONLY)
    filteredRDD.cache()

    /**
     * 统计每日的PV
     */
    val pvRDD = filteredRDD
      // 提取字段, 此处采用编写代码风格为case
      .map {
        case (date, url, guid) => (date, url)
      }
      // 判断URL不能为空
      .filter(tuple => tuple._2.trim.length > 0)
      // 只要 date 有访问记录，就表示用户浏览一次网站
      .map {
        case (date, url) => (date, 1)
      }
      // 此时转换为WordCount词频统计，按照日期进行聚合统计
      .reduceByKey(_ + _)

    // 打印结果RDD中的数据
    // 如果程序运行在Cluster上的时候，下面的打印信息显示在Executor的标出输出日志中。
    pvRDD.foreach(println)
    // (2015-08-28,69197)


    /**
     * 统计每日UV
     */
    val uvRDD = filteredRDD
      // 提取字段, 此处采用编写代码风格为case
      .map {
        case (date, url, guid) => (date, guid)
      }
      // 去重，相同的uig在某一天如果访问多次的话，仅仅算一次
      .distinct()
      // 只要 date 有访问记录，就表示用户当天浏览网站
      .map {
        case (date, guid) => (date, 1)
      }
      // 此时转换为WordCount词频统计，按照日期进行聚合统计
      .reduceByKey(_ + _)

    // 打印结果RDD中的数据
    uvRDD.foreach(println)
    // (2015-08-28,39007)

    // 应该讲缓存中的进行释放
    filteredRDD.unpersist()

    /** ===================================================================*/
    // 在开发测试的时候，为了在每个Application页面监控查看应用中Job的运行
    Thread.sleep(10000000)

    // 关闭资源
    sc.stop()
  }
}
