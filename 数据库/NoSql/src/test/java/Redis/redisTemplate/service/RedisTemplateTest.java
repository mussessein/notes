package java.Redis.redisTemplate.service;

import Redis.redisTemplate.service.UserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTemplateTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("Spring-redis.xml");
        UserService userService = classPathXmlApplicationContext.getBean(UserService.class);
        String key = "test";
        String result = userService.getString("test");
        System.out.println(result);
    }
}
