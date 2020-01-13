官方文档： http://spark.apache.org/docs/latest/sql-data-sources-hive-tables.html 

# SparkSQL

早期统计数据的方式：MapReduce、SQL

但是MR弊端太多，实现复杂，SQL方便，但是面对关系型数据库受限；

Hive的出现，使用类SQL语言（HQL）封装了MR的复杂逻辑，极大简化了MR的复杂度，提升了开发速度；

但是，Spark觉得MR还是太慢，于是Spark模仿Hive执行MR，出现了SparkSQL执行RDD。

------

RDD没有数据结构，为了能够使用类SQL处理RDD数据，Spark进一步将RDD进行封装，提供了两个抽象结构：

- DataFrame
- DataSet

也就是说DataFrame、DataSet底层都是RDD，执行方式是：**将SparkSQL转换成RDD，提交集群**；

三者的关系：

RDD(底层数据)===封装数据结构===>DataFrame===封装类和属性===>DataSet

![](C:/Users/whr/Desktop/Spark/image/dataset.png)

## DataFrame

RDD还是那个RDD，DataFrame为RDD提供了一个**Schema视图**；为RDD的数据提供了结构

![](C:/Users/whr/Desktop/Spark/image/dataframe.png)

### 转换

三种结构的相互转换：

```scala
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
      *转换成RDD之后，通过访问索引，访问字段
      */
    rdd_1.foreach(
        row=>{
            println(row.getString(1))
            /*
  返回结果：quin
          zhangsan
          lisi
           */
        }
    )
    ss.stop
}
```

