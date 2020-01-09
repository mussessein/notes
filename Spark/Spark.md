# Spark

## Spark运行流程

SparkStandalone模式（一个Worker下可以有多个Executor）

后面有yarn模式

![](./image/spark.png)

### SparkContext

Spark 应用程序的入口，负责调度各个运算资源，协调各个 Worker，Node 上的 Executor

### Driver

创建SparkContext（spark上下文对象）的应用程序（或者说main函数）称为Driver；

功能：创建RDD、进行RDD的转化操作、行动操作代码的运行。（除了算子操作，Driver都干）

Driver做什么：

- 将用户程序转化为job—创建SparkContext
- 跟踪Executor的运行状况；
- 给Executor发送Task，调度任务；
- UI显示运行状况—生成DAGScheduler；

### Executor

所有算子的执行，都由Executor执行；

并且：仅执行算子操作，其余都由Driver执行。

- 负责运行Spark应用的某一个任务，结果返回给Driver；
- 可以为RDD提供内存存储；

### Worker Node

集群中任何可以运行Application代码的节点，运行一个或**多个**Executor进程

## Spark Application

**一个Application有多个Job，一个Job内可以有多个Stage，一个Stage内有多个Task**

### Stage

Spark任务会根据**RDD之间的依赖关系，形成一个DAG有向无环图**，DAG会提交给DAGScheduler，DAGScheduler会把DAG划分相互依赖的多个stage，划分stage的依据就是RDD之间的宽窄依赖。**遇到宽依赖就划分stage**,每个stage包含一个或多个task任务。然后将这些task以taskSet的形式提交给**TaskScheduler运行**。 

### Task

一个分区对应一个Task，Task执行RDD中对应Stage中包含的算子。Task被封装好后放入Executor的线程池中执行

## Spark三种模式

|    模式    | Spark安装机器数 |  需启动的进程  | 所属者 |
| :--------: | :-------------: | :------------: | :----: |
|   Local    |        1        |       无       | Spark  |
| Standalone |        3        | Master、Worker | Spark  |
|    Yarn    |        1        |   Yarn、HDFS   | Hadoop |

### local模式：

- local[k]：自定义k个线程数；
- local[*]：根据Cpu数量自行定义；

不需要配置环境，直接解压执行 自带的实例jar包

local模式：计算Pi

```shell
$ bin/spark-submit --class org.apache.spark.examples.SparkPi --executor-memory 1G --total-executor-cores 2 examples/jars/spark-examples_2.11-2.3.1.jar 
100
```
### yarn模式

yarn模式：`--master yarn`

有下面两种模式,，通过`--deploy-mode`参数配置

- yarn-client
- yarn-cluster

如果是集群，下面配置，每台机器都要改；（配置完重启hadoop集群！）

（1）修改hadoop下的yarn-site.xml，添加下面两个配置：

```xml
<property>
    <name>yarn.nodemanager.pmem-check-enabled</name>
    <value>false</value>
</property>
<property>
    <name>yarn.nodemanager.vmem-check-enabled</name>
    <value>false</value>
</property>
```

（2）修改spark-env.sh，添加如下配置

```shell
YARN_CONF_DIR=/home/whr/workbench/hadoop/etc/hadoop
```

yarn-client模式执行wordcount

```shell
$ bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master yarn \
--deploy-mode client \
--driver-memory 1g \
--executor-memory 1g \
--executor-cores 1 \
examples/jars/spark-examples_2.11-2.3.1.jar 100
```

Spark提交任务到Yarn流程：

![](./image/spark-yarn.png)

- Spark-Client提交任务到Yarn的ResourceManager

- ResourceManager在某一个或几个NodeMagager中创建ApplicationMaster

- ApplicationMaster被创建出来，就向ResourceManager申请任务的资源

  （**这里资源就是我需要Container来虚拟内存、Cpu来执行任务，需要创建Executor来执行任务**）

- ResourceManager就返回可以执行任务的资源列表；

- 然后ApplicationMaster就找到一个或多个NodeManager来创建Spark的执行器Executor；

- Executor被创建出来，就向ApplicationMaster反向注册，表示自己准备完毕；

- ApplicationMaster分解任务，分配给Executor执行；

### Standalone模式：

独立部署模式，只用Spark，不用Yarn，可以说是Spark的集群模式；

构建一个由Master+Slave构成的Spark集群，任务在集群中运行；

需要如下配置：（三台机器都要配置）

（1）spark-env.sh

```SHELL
export JAVA_HOME=/home/whr/workbench/jdk1.8
export SCALA_HOME=/home/whr/workbench/scala
export SPARK_MASTER_IP=178.168.3.47	# 注意，这里最好配ip，不然可能UI界面看不到Worker
export SPARK_MASTER_PORT=7077
export SPARK_WORKER_CORES=2
export SPARK_WORKER_MEMORY=1g
```

（2）slaves

```shell
slave1
slave2
```

然后启动集群，不需要hadoop

```shell
~/workbench/spark/sbin$ ./start-all.sh
```

可以用jps命令查看进程：

master节点在运行过程中的进程如下：（因为slaves文件中，也配置了master，所以也有Worker）

```shell
17633 org.apache.spark.executor.CoarseGrainedExecutorBackend	# executor
15845 org.apache.spark.deploy.worker.Worker	# Worker
17549 org.apache.spark.deploy.SparkSubmit	# SparkSubmit
```

slave1、slave2：

```shell
$ jps
1371 Jps
1326 Worker
```

案例启动：

```shell
$ bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://master:7077 \
--executor-memory 1G \
--total-executor-cores 2 \
examples/jars/spark-examples_2.11-2.3.1.jar 100
```

### WordCount

在spark-shell下用scala写WordCount（Local模式）

如果用集群模式：textFile需要写HDFS地址

```scala
sc		// org.apache.spark.SparkContext上下文对象
.textFile("input")		// [String]读取本地数据，返回String，即每行的字符串
.flatMap(_.split(" "))	// [String]分割上一步，每行以空格分割（扁平化）
.map((_,1))			// [(String, Int)]生成（key-value）分割后的每个单词，计次<"hello",1>
.reduceByKey(_+_)	// [(String, Int)]相同key聚合<"hello",5>
.collect				
// 最后结果：Array[(String, Int)] = Array((scala,1), (world,1), (hello,5), (spark,3))
```

wordcount流程：

![](image/sparkrdd.png)

scala的WordCount

```scala
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Spark下scala写wordcount
  * local模式
  */
object WordCount {
  def main(args: Array[String]): Unit = {
    /**
      * SparkConf对象，配置spark框架的部署环境
      * master：ip，默认local
      * AppName：app id
      */
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    /**
      * 1.创建Spark上下文对象sc
      * 2.testFile:读取文件
      * 3.flatMap:扁平化：分解每行String
      * 4.map:数据转化为tuple(key,value)形式
      * 5.reduceByKey:相同key聚合
      */
    val sc = new SparkContext(config)
    val lines: RDD[String] = sc.textFile("src/main/scala/Spark/WordCount/input/")
    val words: RDD[String] = lines.flatMap(_.split(" "))
    val wordToOne: RDD[(String, Int)] = words.map((_, 1))
    val wordToSum: RDD[(String, Int)] = wordToOne.reduceByKey(_ + _)
    val result: Array[(String, Int)] = wordToSum.collect()
    result.foreach(println)
  }
}
```

## Spark数据结构

- RDD：分布式数据集

- 广播变量（BroadcastVariable）：分布式**只读**共享变量

  所有Executor都可以读的共享变量；

- 累加器（Accumulator）：分布式**只写**共享变量

  所有Executor都可以写的共享变量；并且该变量只能够增加；

  只有Driver可以获得累加器的值，Executor只能对其执行增加操作，

### 累加器

为什要用累加器？

如果想在Task计算的时候，统计某些数量，使用累加器是一个方便的方法；

让Partition的数据返回Driver

累加器对象：sc.longAccumulator返回一个累加器对象；

```scala
val accumulator: LongAccumulator = sc.longAccumulator
```

### 广播变量

共享只读变量，是一种调优策略；

高效分发较大的对象给所有的工作节点；

### RDD

## RDD任务划分

<img src="image/stage.png" style="zoom: 67%;" />

- Application：初始化一个SparkContext，即生成一个Application；

- Job：一个Action生成一个Job；

- Stage：根据RDD的依赖关系不同，将Job划分成不同的Stage，每遇到一个宽依赖，划分一个Stage；

  stage（阶段）= 1 + （shuffle个数）

  shuffle是一种宽依赖；shuffle是需要等待所有分区一起shuffle完成，才能进入下一个阶段的。

  窄依赖不需要等待，一对一；

- Task：一个Stage是一个TaskSet（任务集）

  Task任务个数，取决于当前Stage的最后阶段的分区个数；

```
每层任务都是1对多的关系：
Application-->Job-->Stage-->Task
一个Application有多个Job；一个Job有多个Stage；一个Stage有多个Task；
```

可以从UI界面看到Stage的有向无环图；

## 移动数据不如移动计算原则

![](image/move.png)

如果存在这样两个 worker，多个Executor；

那么Task分配给哪个Executor优先级最高：

- Executor-2-2：数据为RDD在内存中；
- Executor-1-1：HDFS的数据就在当前worker下；