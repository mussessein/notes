# RabbitMQ

Overview：

- 生产者将消息发送给Exchange；
- Exchange通过`消息的routing key`和`Exchange与各个Queue的Binding关系`，来判断消息发给哪个Queue；
- Consumer通过TCP连接与Broker建立Connection，一个Connection中创建多个信道Channel，来获取消息；

![](images/rmq1.png)

## 核心概念

### Producer&Consumer

生产者，消费者，不多bb

### Broker

看上图，是一个RabbitMQ服务器的实体，在Producer和Consumer之间；

### Exchange

用来发送消息的交换器；一共**四种交换器**

既然要发送消息，**发给谁**，就要有个规则；

### Binding

表示一种绑定关系，Exchange以此，来决定，消息该发给那个队列；

### Queue

消息队列，不多bb

### Channel

建立在一个Connection中，一个Connection中可以由多个Channel；

即：多个信道，复用一条TCP连接(Connection)

### Virtual Host

可以认为是文件路径，一个用户一个路径；

默认路径是 `/`

## Exchange类型

https://www.bilibili.com/video/av38657363?p=88

此视频讲的很详细

### direct

Headers Exchange与direct一样，但是性能差，已经不用了；

![](images/rmq2.png)

Direct Exchange会根据routing key，将消息发送给**Binding关系完全匹配**的Queue中

### fanout

![](images/rmq3.png)

广播Exchange；

消息会发送到所有的Queue；

### topic

![](images/rmq4.png)

Topic Exchange会根据Binding关系，将routing key进行模糊匹配，发送给匹配到的Queue中；







