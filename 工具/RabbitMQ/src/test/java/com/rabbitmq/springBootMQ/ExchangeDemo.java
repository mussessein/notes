package com.rabbitmq.springBootMQ;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 创建Exchange 的API
 * 在AbstractExchange接口下，
 * 由五个实现类，分别对应5个Exchange
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ExchangeDemo {

    @Autowired
    private AmqpAdmin amqpAdmin;
    // 创建Exchange
    @Test
    public void createExchange(){
        // 创建一个Exchange
        amqpAdmin.declareExchange(new FanoutExchange("fanoutDemo"));
        log.info("创建Exchange成功");
    }
    // 创建队列Queue
    @Test
    public void createQueue(){
        amqpAdmin.declareQueue(new Queue("demo",true));
        log.info("创建Queue成功");
    }
    // 创建binding规则
    @Test
    public void createBinding(){
        // 参数：
        amqpAdmin.declareBinding(
                new Binding(
                        "demo",
                        Binding.DestinationType.QUEUE,
                        "fanoutDemo",
                        "routingDemo",
                        null));
    }
}
