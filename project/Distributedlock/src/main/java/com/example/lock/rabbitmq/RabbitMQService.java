package com.example.lock.rabbitmq;

import com.example.lock.dto.RobbingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;

@Service
@Slf4j
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Environment env;

    public void sendMessage(String msg) {
        try {
            rabbitTemplate.setExchange(env.getProperty("basic.info.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("basic.info.mq.routing.key.name"));
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            Message message = MessageBuilder
                    .withBody(objectMapper.writeValueAsBytes(msg))
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            rabbitTemplate.convertAndSend(message);
        } catch (Exception e) {
            log.error("Send Message Failed => {}",e.getMessage());
        }
    }
    /**
     * 发送crm抢单消息
     */
    public void sendCrmOrderInfo(RobbingDto dto) {
        try {
            rabbitTemplate.setExchange(env.getProperty("crm.order.mq.exchange.name"));
            rabbitTemplate.setRoutingKey(env.getProperty("crm.order.mq.routing.key.name"));

            rabbitTemplate.convertAndSend(dto, new MessagePostProcessor() {
                public Message postProcessMessage(Message message) throws AmqpException {
                    MessageProperties messageProperties=message.getMessageProperties();
                    messageProperties.setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME,RobbingDto.class);
                    return message;
                }
            });
        }catch (Exception e){
            log.error("发送crm抢单消息 生产者发生异常：msg={} ",dto,e.fillInStackTrace());
        }
    }




}
