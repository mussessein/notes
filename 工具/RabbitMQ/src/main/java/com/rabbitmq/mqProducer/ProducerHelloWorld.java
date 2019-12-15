package com.rabbitmq.mqProducer;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.util.ConnectionUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * helloworld测试MQ
 */
@Slf4j
public class ProducerHelloWorld {

    private final static String QUEUE_NAME = "hello";
    private final static String MESSAGE = "hello,world";

    public static void main(String[] args) {
        try{
            Connection connection = ConnectionUtils.getConnection();
            // 连接创建Channel
            Channel channel = connection.createChannel();
            // 队列声明
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // message以字节流的形式发送
            channel.basicPublish("",QUEUE_NAME,null,MESSAGE.getBytes());
            System.out.println("send message:"+MESSAGE);
        }catch (Exception e){
            log.error("发送失败",e.getMessage());
        }
    }
}
