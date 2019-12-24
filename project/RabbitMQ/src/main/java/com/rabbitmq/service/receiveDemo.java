package com.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class receiveDemo {


    @RabbitListener(queues = "${mq.queue}", containerFactory = "singleListenerContainer")
    public void testReceive(Object o) {
        log.info("Receive:{}",o);
    }
}
