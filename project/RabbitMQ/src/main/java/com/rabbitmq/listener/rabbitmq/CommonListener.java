package com.rabbitmq.listener.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
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
public class CommonListener {

    @Autowired
    private ObjectMapper objectMapper;

//    @Value("${basic.info.mq.queue.name}")
//    private String basicQueue;
    /**
     * 监听消费消息
     * @param message
     */
    @RabbitListener(queues = "${basic.info.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeMessage(Message message){
        try {
            byte[] body = message.getBody();
            //TODO：接收对象
            User user=objectMapper.readValue(body, User.class);
            log.info("接收到消息=>{} ",user);

            //TODO：接收String
//            String result=new String(body,"UTF-8");
//            log.info("接收到消息=>{} ",result);

            //TODO：接收多类型字段信息
//            Map<String,Object> resMap=objectMapper.readValue(body,Map.class);
//            log.info("接收到消息=>{} ",resMap);
        }catch (Exception e){
            log.error("监听消费消息 发生异常： ",e.fillInStackTrace());
        }
    }
}

















