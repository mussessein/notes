package Spark.SparkSQL
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
object DF_Transform {
  /**
    * rdd,DF,DS之间的转换
    * 转换需要隐式转换对象(扩展功能)
    */
    def main(args: Array[String]): Unit = {

      val sparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL")
      val ss: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
      // 引入隐式转换对象
      import ss.implicits._
      // SparkSession也能创建RDD,因为DataFrame底层就是RDD
      val rdd: RDD[(Int, String, Int)] = ss.sparkContext.makeRDD(List((1,"zhangsan",12),(2,"lisi",17),(3,"quin",17)))

      // rdd===>DF
      val df: DataFrame = rdd.toDF("id","name","age")
      // DF====>DS：需要一个与表名对应的class，类似于JavaBean
      val ds: Dataset[User] = df.as[User]
      // DS====>DF
      val df_1: DataFrame = ds.toDF()
      // DF===>rdd
      val rdd_1: RDD[Row] = df_1.rdd

      /**
        * 转换成RDD之后，通过访问索引，访问字段
        */
      rdd_1.foreach(
        row=>{
          println(row.getString(1))
          /*
          quin
          zhangsan
          lisi
           */
        }
      )
      ss.stop
    }
}
