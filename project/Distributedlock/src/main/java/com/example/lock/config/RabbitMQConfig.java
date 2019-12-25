package com.example.lock.config;

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
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:rabbitmq.properties")
@Slf4j
public class RabbitMQConfig {
    @Autowired
    private SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory;
    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
    /**
     * 配置消息转化器为Json格式
     * @return
     */

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

    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        factory.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.concurrency",int.class));
        factory.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.max-concurrency",int.class));
        factory.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.prefetch",int.class));
        return factory;
    }
    @Bean
    public RabbitTemplate rabbitTemplate(){
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.SIMPLE);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
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

    //TODO：基本消息模型构建

    @Bean
    public DirectExchange basicExchange(){
        return new DirectExchange(env.getProperty("basic.info.mq.exchange.name"), true,false);
    }

    @Bean(name = "basicQueue")
    public Queue basicQueue(){
        return new Queue(env.getProperty("basic.info.mq.queue.name"), true);
    }

    @Bean
    public Binding basicBinding(){
        return BindingBuilder.bind(basicQueue()).to(basicExchange()).with(env.getProperty("basic.info.mq.routing.key.name"));
    }


    //TODO：抢单队列模型

    @Bean
    public TopicExchange crmOrderExchange(){
        return new TopicExchange(env.getProperty("crm.order.mq.exchange.name"), true,false);
    }

    @Bean(name = "crmOrderQueue")
    public Queue crmOrderQueue(){
        return new Queue(env.getProperty("crm.order.mq.queue.name"), true);
    }

    @Bean
    public Binding crmOrderBinding(){
        return BindingBuilder.bind(crmOrderQueue()).to(crmOrderExchange()).with(env.getProperty("crm.order.mq.routing.key.name"));
    }
}
