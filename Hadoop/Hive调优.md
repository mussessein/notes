# Hive调优

## fetch模式

```xml
<property>
    <name>hive.fetch.task.conversion</name>
    <value>more</value>
    <description>
        Expects one of [none, minimal, more].
        Some select queries can be converted to single FETCH task minimizing latency.
        Currently the query should be single sourced not having any subquery and should not have
        any aggregations or distincts (which incurs RS), lateral views and joins.
        0. none : disable hive.fetch.task.conversion
        1. minimal : SELECT STAR, FILTER on partition columns, LIMIT only
        2. more    : SELECT, FILTER, LIMIT only (support TABLESAMPLE and virtual columns)
    </description>
</property>
```

抓取模式：

- more：默认的模式：表示全局查找，字段查找，limit查找都不启用MR；
- minimal：
- none：所有程序都走MR；

```shell
hive (default)> set hive.fetch.task.conversion;
hive.fetch.task.conversion=more
```

## 本地模式

```xml
<property>
    <name>hive.exec.mode.local.auto</name>
    <value>false</value>  <!-- 默认关闭本地模式 -->
</property>
<property>
    <name>hive.exec.mode.local.auto.inputbytes.max</name>
    <value>134217728</value><!-- 文件超过此值，自动关闭本地模式 -->
</property>
<property>
    <name>hive.exec.mode.local.auto.input.files.max</name>
    <value>4</value><!-- 文件数量大于4，自动关闭本地模式 -->
</property>
```

在单台机器上处理所有任务，对于小数据集，文件数量少的任务，执行时间可以被明显缩短；

- 开启本地模式

  ```shell
  hive (default)> set hive.exec.mode.local.auto=true;
  ```

- 设置本地模式的数据集大小，文件数量：

  ```shell
  hive (default)> set hive.exec.mode.local.auto.inputbytes.max=134217728;
  hive (default)> set hive.exec.mode.local.auto.input.files.max=4;
  ```

## 表优化

### 大表小表Join

数据量小的表，放在join的左边，可以减少内存溢出发生的几率；

新版的hive，已经默认打开此优化（MapJoin优化），小表放左右，已经没有明显区别了；

### 大表 Join 大表

1. 空Key过滤

   提前对空Key，进行过滤

   ```sql
   where id is not null
   ```

2. 空Key造成数据倾斜

   给空字段，赋随机字符串（不要与非空key相同）+随机值，让这些空字段，不会进到同一个Reduce中，避免数据倾斜；

   ```sql
   on case when n.id is null then concat('hive',rand()) else n.id = 0.id;
   ```

### MapJoin—内存换时间

将小表加载进内存，进行map；

```xml
<property>
    <name>hive.auto.convert.join</name>
    <value>true</value><!-- 默认开启MapJoin -->
</property>
<property>
    <name>hive.mapjoin.smalltable.filesize</name>
    <value>25000000</value><!-- 小表加载进内存的大小 -->
</property>
```

MapJoin是Hive的一种优化操作，其适用于小表JOIN大表的场景，进行自动优化；

Hive中的Join可分为

- Common Join：Reduce阶段完成join
- Map Join：Map阶段完成join，不需要进入到Reduce阶段才进行连接，节省了在Shuffle阶段时要进行的大量数据传输；

使用场景：

并不是所有场景都适合MapJoin，适合的场景如下：

- 小表 Join 大表：小表会被放入内存中，不影响性能；
- 不等值的链接操作；

## Group By 优化

Map阶段后，会将同一个Key，发给同一个Reduce，如果group by的某一部分过大，就会发生数据倾斜；

数据倾斜之后，在Reduce端进行聚合就会造成资源浪费；

所以：并不是所有的聚合操作，都需要在Reduce端进行操作，很多聚合操作都可以在Map端进行。

开启Map端聚合：

```xml
<property>
    <name>hive.map.aggr</name>
    <value>true</value>
</property>
<property>
    <name>hive.groupby.mapaggr.checkinterval</name>
    <value>100000</value><!-- map端进行聚合的操作数目 -->
</property>
<property>
    <name>hive.groupby.skewindata</name>
    <value>false</value><!--默认false：-->
    <!--有数据倾斜的时候自动进行均衡负载（将Map的结果随机分发给不同的Reduce，不再通过Key分发-->
</property>
```

## 动态分区

提前设置好如何分区，在插入数据的时候，Hive根据字段的值，自动进行分区。而不需要每次加载数据手动指定分区；

默认是：开启动态分区，非严格模式；

```shell
hive.exec.dynamic.partition=true
hive.exec.dynamic.partition.mode=nonstrict
```

- strict（严格模式）：必须指定至少一个分区为静态分区；
- nonstrict（非严格模式）：允许所有的分区字段都可以使用动态分区；

在所有的MR的节点上，最大一共可以创建的动态分区数目：

```xml
<property>
    <name>hive.exec.max.dynamic.partitions</name>
    <value>1000</value>
</property>
<property>
    <name>hive.exec.max.dynamic.partitions.pernode</name>
    <value>100</value><!--每个节点上，最大能创建的动态分区数-->
</property>
<property>
    <name>hive.exec.max.created.files</name>
    <value>100000</value><!--整个MRjob，最大创建的HDFS文件数-->
</property>
```

例子：

已有分区表：

```sql
create table dept(dname string,loc int)
partitioned by (deptno int)
row format delimited fields terminated by '\t';
```

在插入数据的时候，指定动态分区列：

```sql
insert into table dept partition(deptno)
select dname, loc deptno form dept_table;
```

## MR优化

Map：

在MR层面的优化，主要是针对MapReduce性能的优化

1. 小文件提前合并
2. 复杂文件增加map操作数量

Reduce：

Reduce任务个数，默认-1，根据Map阶段输入之前的数据量来定的；

每个Reduce的默认处理数据量为256MB；输入数据1G，就会产生4个reduce;

手动设置Reduce任务数：

```shell
set mapreduce.job.reduces=15
```

## 并行执行

