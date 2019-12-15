package com.rabbitmq.springBootMQ;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringBoot整合MQ
 * 1. 配置文件配置好RabbitTemplate，exchange，routing.key，queue
 * 2. 发送逻辑，接受逻辑
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class senderDemo {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Environment env;
    private final static String  MSG="testDemo";

    /**
     * 接受测试见：
     */
    @Test
    public void send(){
        rabbitTemplate.setExchange(env.getProperty("mq.exchange"));
        rabbitTemplate.setRoutingKey(env.getProperty("mq.routing.key"));
        rabbitTemplate.convertAndSend(MSG);
    }

}
