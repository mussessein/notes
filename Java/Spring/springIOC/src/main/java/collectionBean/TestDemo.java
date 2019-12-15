package collectionBean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    @Test
    public void demo(){

        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Collection.xml");
        CollectionBean collectionBean=(CollectionBean)applicationContext.getBean("CollectionBean");
        System.out.println(collectionBean);

    }
}
