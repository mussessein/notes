package process;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {

    /*
    spring的单例模式
     */
    @Test
    public void demo1(){
        //创建对象
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Person.xml");
        //工厂创建实例
        Person person1=(Person)applicationContext.getBean("Person");
        Person person2=(Person)applicationContext.getBean("Person");
        //创建的两个对象为同一个对象，单例
        System.out.println(person1);
        System.out.println(person2);
        //关闭工厂，以销毁对象
        ((ClassPathXmlApplicationContext) applicationContext).close();
    }
    @Test
    public void demo2(){
        /*
        spring 执行步骤
         */
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Person.xml");
        Person person1=(Person)applicationContext.getBean("Person");
        person1.run();
        ((ClassPathXmlApplicationContext) applicationContext).close();

    }
}
