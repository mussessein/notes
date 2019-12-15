package com.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 用来配置
 * 1. 消息转换器：
 * RabbitMQ默认的消息使用java-serialized-object的序列化，需要自己配置消息转换器为Json
 * 2. 队列
 * 3. 路由
 * 4. 交换器
 */
@Configuration
@Slf4j
public class RabbitConfig {
    @Autowired
    private SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory;
    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;
    /**
     * 配置消息转化器为Json格式
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(){
        //
        SimpleRabbitListenerContainerFactory simpleContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleContainerFactory.setConnectionFactory(connectionFactory);
        simpleContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        simpleContainerFactory.setConcurrentConsumers(1);
        simpleContainerFactory.setMaxConcurrentConsumers(1);
        simpleContainerFactory.setPrefetchCount(1);
        simpleContainerFactory.setBatchSize(1);
        return simpleContainerFactory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功==>correlationData({}),ack({}),cause({})", correlationData, ack, cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息丢失==>exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Queue successEmailQueue(){
        return new Queue(env.getProperty("mq.queue"),true);
    }
    @Bean
    public TopicExchange successEmailExchange(){
        return new TopicExchange(env.getProperty("mq.exchange"),true,false);
    }
    @Bean
    public Binding successEmailBinding(){
        return BindingBuilder.bind(successEmailQueue()).to(successEmailExchange()).with(env.getProperty("mq.routing.key"));
    }
}
