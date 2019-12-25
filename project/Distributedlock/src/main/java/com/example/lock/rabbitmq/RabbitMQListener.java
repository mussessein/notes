package com.example.lock.rabbitmq;

import com.example.lock.dto.RobbingDto;
import com.example.lock.service.CrmOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
@Component
@Slf4j
public class RabbitMQListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CrmOrderService crmOrderService;

    /**
     * 基本消息队列
     * @param msg
     */
    @RabbitListener(queues = "${basic.info.mq.queue.name}",containerFactory = "singleListenerContainer")
    public void consumeBasicInfo(String msg){
        try {
            log.info("基本消息队列 监听到消息：{} ",msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * crm系统抢单队列：
     * 使用高并发的多消费者容器multiListenerContainer
     * 可以设置ConcurrentConsumers并发消费者实例
     */
    @RabbitListener(queues = "${crm.order.mq.queue.name}",containerFactory = "multiListenerContainer")
    public void consumeCrmOrderInfo(RobbingDto dto){
        try {
            log.info("crm系统抢单队列 监听到消息：{} ",dto);
            // 收到mq的消息，执行抢单逻辑
            crmOrderService.robbingV3(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
