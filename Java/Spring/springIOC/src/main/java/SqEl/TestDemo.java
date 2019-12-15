package SqEl;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    @Test
    public void demo(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("SqEL.xml");
        Product product=(Product)applicationContext.getBean("Product");
        System.out.println(product);


    }
}
