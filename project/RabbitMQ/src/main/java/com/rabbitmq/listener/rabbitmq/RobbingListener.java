package com.rabbitmq.listener.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.service.concurrency.ConcurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
@Slf4j
public class RobbingListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ConcurrencyService concurrencyService;


    /**
     * 监听抢单消息
     * @param message
     */
    @RabbitListener(queues = "${product.robbing.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMessage(Message message){
        try {
            byte[] body = message.getBody();
            String mobile=new String(body,"UTF-8");
            log.info("监听到抢单手机号： {} ",mobile);
            concurrencyService.manageRobbing(String.valueOf(mobile));
        }catch (Exception e){
            log.error("监听抢单消息 发生异常： ",e.fillInStackTrace());
        }
    }

}

















