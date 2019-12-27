package com.rabbitmq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.rabbitmq.dto.User;
import com.rabbitmq.response.BaseResponse;
import com.rabbitmq.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * RabbitMQ的producer三个类型测试：
 * 1. 普通String消息
 * 2. Object消息
 * 3. Collection消息
 */
@RestController
@RequestMapping("rabbit")
@Slf4j
public class RabbitCommonController {

    @Value("${basic.info.mq.exchange.name}")
    private String basicExchange;
    @Value("${basic.info.mq.routing.key.name}")
    private String basicRoutingKey;
    @Autowired
    private Environment env;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    /**
     * 发送普通String消息
     * @param message
     * @return
     */
    @RequestMapping(value = "/simple/message/send",method = RequestMethod.GET)
    public BaseResponse sendSimpleMessage(@RequestParam String message){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("待发送的消息=>{} ",message);

            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
            rabbitTemplate.setExchange(basicExchange);
            rabbitTemplate.setRoutingKey(basicRoutingKey);
            byte[] bytes = objectMapper.writeValueAsBytes(message);
            Message msg=MessageBuilder.withBody(bytes).build();
            rabbitTemplate.convertAndSend(msg);
        }catch (Exception e){
            log.error("发送简单消息发生异常： ",e.fillInStackTrace());
        }
        return response;
    }


    /**
     * 发送Object消息
     * @param user
     * @return
     */
    @PostMapping(value = "/object/message/send",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse sendObjectMessage(@RequestBody User user){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            log.info("待发送的消息=>{} ",user);

            rabbitTemplate.setExchange(basicExchange);
            rabbitTemplate.setRoutingKey(basicRoutingKey);
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

            Message msg=MessageBuilder.withBody(objectMapper.writeValueAsBytes(user)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
            rabbitTemplate.convertAndSend(msg);
        }catch (Exception e){
            log.error("发送对象消息发生异常： ",e.fillInStackTrace());
        }
        return response;
    }


    /**
     * 发送Collection消息
     * @return
     */
    @RequestMapping(value = "/collection/message/send",method = RequestMethod.GET)
    public BaseResponse sendMultiTypeMessage(){
        BaseResponse response=new BaseResponse(StatusCode.Success);
        try {
            Integer id=120;
            String name="axiuluio";
            Long longId=12000L;
            Map<String,Object> dataMap= Maps.newHashMap();

            dataMap.put("id",id);
            dataMap.put("name",name);
            dataMap.put("longId",longId);
            log.info("待发送的消息=>{} ",dataMap);
            rabbitTemplate.setExchange(basicExchange);
            rabbitTemplate.setRoutingKey(basicRoutingKey);
            rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

            Message msg=MessageBuilder.withBody(objectMapper.writeValueAsBytes(dataMap)).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
            rabbitTemplate.convertAndSend(msg);
        }catch (Exception e){
            log.error("发送多类型字段消息发生异常： ",e.fillInStackTrace());
        }
        return response;
    }
}





























