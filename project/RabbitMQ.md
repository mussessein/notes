# RabbitMQ

Overview：

- 生产者将消息发送给Exchange；
- Exchange通过`消息的routing key`和`Exchange与各个Queue的Binding关系`，来判断消息发给哪个Queue；
- Consumer通过TCP连接与Broker建立Connection，一个Connection中创建多个信道Channel，来获取消息；

![](img/rmq1.png)

## 核心概念

### Producer&Consumer

生产者，消费者，不多bb

### Broker

看上图，是一个RabbitMQ服务器的实体，在Producer和Consumer之间；

### Exchange

用来发送消息的交换器；一共**四种交换器**

既然要发送消息，**发给谁**，就要有个规则；

Exchange属性：

- name
- Type：四种（direct，fanout，topic，headers）具体看后续
- Durability：是否需要持久化；
- Auto Delete：当没有队列绑定到Exchange上，是否自动删除Exchange
- Internal：直接false，基本不用

### Binding

表示一种绑定关系，Exchange以此，来决定，消息该发给那个队列；

### Queue

消息队列

属性：

- name
- Durability：持久化
- Transient：不持久化
- Auto delete：当最后一个监听（Listener）移除之后，是否自动删除队列
- 此外还有很多参数：TTL，Max Length等等

### Message

就是消息，以字节传输；

属性：

- Header：消息头，含有此条消息的属性，配置
  - priority：指定优先级
  - content_encoding：一般就是UTF-8
  - content_type：类似于HTTP请求头里面的ContentType
  - **还有很多属性**
- Body：消息体，就是要传输的数据本身
- delivery mode：

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

![](img/rmq2.png)

Direct Exchange会根据routing key，将消息发送给**Binding关系完全匹配**的Queue中

### fanout

![](img/rmq3.png)

广播Exchange；

**不需要配置routing key，消息会发送到所有的Queue；**

- 性能最好，最快！

### topic

![](img/rmq4.png)

Topic Exchange会根据Binding关系，将routing key进行模糊匹配，发送给匹配到的Queue中；

- #：匹配一个或多个词
- *：匹配一个词；

## 确认机制

 https://www.cnblogs.com/haixiang/p/10900005.html 

### Producer端

Producer端会有一个Listener监听器，接收投递状态的消息；

producer端的消息投递有两种状态：confirm，return

**confrm**，消息送到broker可能产生两个状态：

- ack：消息成功投递
- nack：消息被拒

**return**，消息被broker接收之后，没匹配到合适的队列，就返回给了producer，也叫做死信；

开启发送者确认机制回调：**异步确认消息发送到RabbitMQ中**

```properties
spring.rabbitmq.publisher-confirms = true	# 开启确认机制
spring.rabbitmq.publisher-returns = true	# 开启回调
```

### 消费者确认

三种确认：

```properties
# 不确认
AcknowledgeMode.NONE
# 自动确认
AcknowledgeMode.AUTO
# 手动确认
AcknowledgeMode.MANUAL
```

springBoot配置

```properties
spring.rabbitmq.listener.simple.acknowledge-mode = manual,auto,none
```







## 100%投递

如何保障消息100%成功投递到RaabitMQ中？



## 消息限流



## TTL

TTL：Time To Live（生存时间）

- 从消息入队开始算，超过TTL，消息自动清除；
- 在创建队列的时候，可以指定TTL

## 死信队列

![](img/rmq5.png)

当一条消息，在Queue中变成了死信，这条消息，就会被重新发送到另外一个Exchange（叫做死信交换机），通过死信路由另外一个Queue中，这个Queue就叫做**死信队列**；

变成死信的条件：

- 消息被拒绝；
- 消息TTL过期；
- 队列满了；

## RabbitMQ三种集群模式

- 单一模式
- 普通模式：多个RabbitMQ的结构相同，但是消息只存在于一台机器上，其他机器需要的时候，再拉取；
- 镜像模式（HA集群）：消息会不断在不同节点之间同步；
  - 主节点提供读写，备用节点不提供读写
  - 不适合高并发场景，集群间的数据同步，占用网络带宽；适合可靠性要求高的场景；

## SpringBoot整合RabbitMQ







## 面试

**1. 如果 RabbitMQ 服务器宕机了，消息会丢失吗？**

```
不会丢失，RabbitMQ 服务器支持消息持久化机制，会把消息持久化到硬盘上。
```

**2. 如何确保消息正确地发送至RabbitMQ？**

RabbitMQ 使用**发送方确认模式**，确保消息正确地发送到 RabbitMQ。

**发送方确认模式**：

将信道设置成 confirm 模式（发送方确认模式），则所有在信道上发布的消息都会被指派一个唯一的ID。一旦消息被投递到目的队列后，或者消息被写入磁盘后（可持久化的消息），信道会发送一个确认给生产者（包含消息唯一ID）。如果RabbitMQ发生内部错误从而导致消息丢失，会发送一条nack（not acknowledged，未确认）消息。

发送方确认模式是异步的，生产者应用程序在等待确认的同时，可以继续发送消息。当确认消息到达生产者应用程序，生产者应用程序的回调方法就会被触发来处理确认消息

## RabbitMQ面试

### 消息队列的作用与使用场景

**场景1：用户注册+邮件通知注册成功+发送短讯通知注册成功**

串行：三个动作串行执行，最后页面返回用户注册成功===>耗时150ms（基本不会用）

并行：首先用户注册，写入数据库，然后启动两个并行线程分别执行邮件和短信，再返回用户注册成功====>耗时100ms；

消息队列：用户注册，写入数据库成功，直接返回用户注册成功===>耗时50ms

​				   发送用户消息到消息队列，消费者模块监听队列，异步处理邮件发送，短讯发送的逻辑；

**场景2：应用解耦===>订单系统，库存系统**

传统：订单系统调用库存系统接口，弊端：库存系统故障，订单系统失败（不应该如此，无论如何业务不能中断）

消息队列：订单系统下单之后，写入消息到消息队列（持久化消息），直接返回用户下单成功，

​					库存系统异步监听队列，即使库存系统挂了，消息没有丢失，重启，仍能执行库存操作；

**场景3：流量削峰===>秒杀活动**

秒杀开始瞬间，大量请求，如果直接进入后端，会挂掉；

秒杀活动，流量大，但是有效流量小，需要拦截掉大量无效流量，使得高于有效流量，又不至于过大流量，进入后端；这就需要消息队列；

### 无法被路由的消息去了哪里

routing key没有正确绑定，造成消息无法发送给对应的队列；

- 使用 **mandatory** 参数：

  `mandatory = true` 表示当交换器无法根据routingKey发送消息时，会调用 **Basic.Return** 命令将消息返回给生产者，生产者通过监听 **RetrunListener** 来接收没有被路由的消息；

  `mandatory = false` 默认丢弃无法路由的消息

- 使用备份 **Exchange**：

  ` "alternate-exchange",AE ` 配置添加到Channel配置中，这样无法路由的消息发送给备份交换器，再次路由，此交换器一般为`fanout`

SpringBoot中，配置RabbitTeplate

~~~java
// 确认回调（消息发送到Exchange，调用此方法确认）
rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息发送成功==>correlationData({}),ack({}),cause({})", correlationData, ack, cause);
    }
});
// 消息丢失（消息无法正确路由，主动丢弃，返回给生产者，调用此方法）
rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息丢失==>exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
    }
});
~~~



```
3.无法被路由的消息去了哪里

4.消息在什么时候会变成Dead Letter（死信）

5.RabbitMQ如何实现延时队列

6.如何保证消息的可靠性投递

7.消息幂等性

8.如何在服务的和消费端做限流

9.如何保证消息的顺序性

10.RabbitMQ集群模式和集群节点类型
```

