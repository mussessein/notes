package StrongMethod;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    @Test
    public void demo(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao=(UserDao) applicationContext.getBean("UserDao");
        userDao.create();
        userDao.save();
        userDao.destroy();
    }

}
