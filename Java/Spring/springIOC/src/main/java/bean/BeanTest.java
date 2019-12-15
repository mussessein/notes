package bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {
/*
无论运行什么方法，spring会自动实例化xml配置文件中的所有类，
并且默认为单例模式，只会创建一个实例！！！！！！！！！！！！！
 */
    @Test
    public void bean1(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean1-3.xml");
        //通过工厂获得实例：
        Bean1 bean1=(Bean1) applicationContext.getBean("bean1");
    }
    @Test
    public void bean2(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean1-3.xml");
        //通过工厂获得实例：
        Bean2 bean2=(Bean2) applicationContext.getBean("bean2");
        //此对象的实例不会被创建，因为静态方法，只需要类加载就可以
    }
    @Test
    public void bean3(){
        //创建工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Bean1-3.xml");
        //通过工厂获得实例：
        Bean3 bean3=(Bean3) applicationContext.getBean("bean3");
        //此工厂的实例也会被IOC自动创建，因为不是静态方法，需要实例才能创建Bean3的实例
    }
}
