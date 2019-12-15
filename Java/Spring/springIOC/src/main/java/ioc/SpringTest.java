package ioc;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class SpringTest {

    @Test
    /*
    传统开发方式
     */
    public void demo_1(){
        //无属性的创建
        UserService userService1=new UserServiceImpl();
        userService1.sayHello();
        //带有属性的创建,需要重新修改代码
        UserServiceImpl userService2=new UserServiceImpl();
        userService2.setName("zhang");
        userService2.sayHello();
    }

    @Test
    /*
    Spring方式:通过xml配置文件创建类
    （创建对象的控制权交给了spring）
    在加入了对象属性之后，也不需要修改代码，只需要在配置文件中增加属性就可以了
     */
    public void demo_2(){
        //创建Spring工厂
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        //通过工厂获得类,id 就是在配置文件中的id，
        UserService userService=(UserService) applicationContext.getBean("UserService");

        userService.sayHello();

    }
    /*
    读取本地磁盘中的配置文件
     */
    @Test
    public void demo_3(){
        //创建SPring的工厂类
        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("d:/Code/applicationContext.xml");
        //通过工厂获得类,id 就是在配置文件中的id，
        UserService userService=(UserService) applicationContext.getBean("UserService");

        userService.sayHello();
    }
}
