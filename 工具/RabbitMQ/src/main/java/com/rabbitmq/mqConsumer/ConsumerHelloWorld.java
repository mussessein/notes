package com.rabbitmq.mqConsumer;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.util.ConnectionUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * helloworld测试MQ
 */
@Slf4j
public class ConsumerHelloWorld {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        DeliverCallback deliverCallback = (consumerTag,deliverry)->{
            String message = new String(deliverry.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }

}
