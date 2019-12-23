package com.secondkill.config;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * RabbitMQ通用配置
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    @Autowired
    private Environment env;
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;
    /**
     * 单一消费者
     * 监听容器工厂
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(){
        SimpleRabbitListenerContainerFactory simpleContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleContainerFactory.setConnectionFactory(connectionFactory);
        // 设置此容器的消息转换器
        simpleContainerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 当前消费者数量
        simpleContainerFactory.setConcurrentConsumers(1);
        // 最大消费者数量
        simpleContainerFactory.setMaxConcurrentConsumers(1);
        // 消费失败，是否重回队列
        simpleContainerFactory.setDefaultRequeueRejected(false);
        // 设置消息确认机制
        simpleContainerFactory.setAcknowledgeMode(AcknowledgeMode.NONE);
        simpleContainerFactory.setPrefetchCount(1);
        simpleContainerFactory.setBatchSize(1);
        return simpleContainerFactory;
    }
    /**
     * 多消费者
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory multiListenerContainer = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(multiListenerContainer,connectionFactory);
        multiListenerContainer.setMessageConverter(new Jackson2JsonMessageConverter());
        // 消费模式：确认机制
        multiListenerContainer.setAcknowledgeMode(AcknowledgeMode.NONE);
        multiListenerContainer.setConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.concurrency",int.class));
        multiListenerContainer.setMaxConcurrentConsumers(env.getProperty("spring.rabbitmq.listener.simple.max-concurrency", int.class));
        multiListenerContainer.setPrefetchCount(env.getProperty("spring.rabbitmq.listener.simple.prefetch", int.class));
        return multiListenerContainer;
    }

    /**
     * RabbitTemplate：
     * 用来发送接收消息的组件
     * 1. 不需要写此Bean，也可以，因为RabbitMQ-Starter已经内置了
     * 2. 重写RabbitTemplate后，所有的消息发送，接受，都会经由重写的RabbitTemplate执行
     * @return
     */
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

    /**
     * 第一个模型：异步发送邮件的消息模型
     * 1. 设置队列名字
     * 2. 设置Exchange为Topic类型
     * 3. 设置Binding关系：通过routingkey将Queue和Exchange绑定起来
     */
    @Bean
    public Queue successEmailQueue(){
        return new Queue(env.getProperty("mq.kill.item.success.email.queue"),true);
    }
    @Bean
    public TopicExchange successEmailExchange(){
        return new TopicExchange(env.getProperty("mq.kill.item.success.email.exchange"),true,false);
    }
    @Bean
    public Binding successEmailBinding(){
        return BindingBuilder.bind(successEmailQueue()).to(successEmailExchange()).with(env.getProperty("mq.kill.item.success.email.routing.key"));
    }
    /**
     * 第三个模型：秒杀成功的消息模型
     * 1. queue：秒杀成功的info，会存入这里
     * 需要绑定死信交换机和routing key
     * （1）就是说：当这个里面的消息，到达TTL时间（超时未支付时间），就会进入死信队列（第三个模型）
     * （2）正常支付的消息，就会被正常消费，创建订单，最终完成秒杀
     * @return
     */
    @Bean
    public Queue successKillDeadQueue(){
        Map<String, Object> argsMap= Maps.newHashMap();
        // 死信队列必须参数：x-dead-letter-exchange
        argsMap.put("x-dead-letter-exchange",env.getProperty("mq.kill.item.success.kill.dead.exchange"));
        argsMap.put("x-dead-letter-routing-key",env.getProperty("mq.kill.item.success.kill.dead.routing.key"));
        return new Queue(env.getProperty("mq.kill.item.success.kill.dead.queue"),true,false,false,argsMap);
    }

    @Bean
    public TopicExchange successKillDeadProdExchange(){
        return new TopicExchange(env.getProperty("mq.kill.item.success.kill.dead.prod.exchange"),true,false);
    }
    @Bean
    public Binding successKillDeadProdBinding(){
        return BindingBuilder.bind(successKillDeadQueue()).to(successKillDeadProdExchange()).with(env.getProperty("mq.kill.item.success.kill.dead.prod.routing.key"));
    }
    /**
     * 第二个模型：死信队列模型
     * 1. 死信交换机：绑定的是第二个模型的Queue，就是超时未支付的消息，就会通过这个交换机，发送给死信队列
     * 2. 队列：真正的死信队列
     * 最后可以将这些消息，拿出来，发送秒杀失败的消息；
     */
    @Bean
    public Queue successKillRealQueue(){
        return new Queue(env.getProperty("mq.kill.item.success.kill.dead.real.queue"),true);
    }
    // 死信交换机
    @Bean
    public TopicExchange successKillDeadExchange(){
        return new TopicExchange(env.getProperty("mq.kill.item.success.kill.dead.exchange"),true,false);
    }
    @Bean
    public Binding successKillDeadBinding(){
        return BindingBuilder.bind(successKillRealQueue()).to(successKillDeadExchange()).with(env.getProperty("mq.kill.item.success.kill.dead.routing.key"));
    }
}
