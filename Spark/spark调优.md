## 避免重复计算

```scala
val rdd = sc.parallelize(1 to 5)
// Transformation
val rddMap = rdd.map(_ + 1)
// Action
rddMap.reduce()
rddMap.count()
rddMap.take(2)
```

每一个Action操作，都会从源头再次`map`，这样就造成重复计算

解决方法：

1. 对于一份数据执行多次算子操作时，只使用一个RDD。
2. 对多次使用的RDD进行**持久化persist( )**；

```scala
val rdd = sc.parallelize(1 to 5)
// 这里只会计算一次，因为血缘在下面被打断
val rddMap = rdd.map(_ + 1)	
// 打断血缘,数据以序列化的形式缓存在JVM的HEAP中
rddMap.persist(StorageLevel.MEMORY_ONLY)	
rddMap.first()
rddMap.count()
rddMap.take(2)
// 释放缓存，必须手工释放
rddMap.unpersist()
```

持久化级别：

|         级别         | 使用空间 | CPU时间 |   位置    |            备注            |
| :------------------: | :------: | :-----: | :-------: | :------------------------: |
| MEMORY_ONLY（默认）  |    高    |   低    |   内存    |                            |
|   MEMORY_ONLY_SER    |    低    |   低    |   内存    |                            |
|   MEMORY_ADN_DISK    |    高    |  中等   | 磁盘+内存 | 内存中放不下，就溢写到磁盘 |
| MEMORY_ONLY_DISK_SER |    低    |   高    | 磁盘+内存 | 内存中放不下，就溢写到磁盘 |
|      DISK_ONLY       |    高    |   高    |   磁盘    |                            |

