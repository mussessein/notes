package com.rabbitmq.springBootMQ;

import com.rabbitmq.pojo.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class directDemo {
    /**
     * 直接注入
     */
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 1. 点对点:direct
     * 2. 需要direct Exchange
     * 3.
     */
    @Test
    public  void directSend(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "hello");
        Book book = new Book("hello", "world");
        /**
         * 参数：
         * 1. exchange：
         * 2. routing key
         * 3. message:默认被序列化
         */

        rabbitTemplate.convertAndSend("directDemo","hello",book);
    }
    @Test
    public void directReceive() {
        // 传入Queue的name
        Object msg = rabbitTemplate.receiveAndConvert("hello");
        log.info("Receive:{}",msg);
    }
}
