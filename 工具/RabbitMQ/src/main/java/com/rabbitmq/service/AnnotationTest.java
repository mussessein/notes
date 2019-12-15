package com.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AnnotationTest {


    /**
     * 注解方式
     */
    @RabbitListener(queues = "hello")
    public void receive(Object o){
        log.info("Receive:{}",o);
    }



}
