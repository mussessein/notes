package properties;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemo {
    @Test
    /*
     <!--构造器的注入 设置-->
     */
    public void demo1(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("properties.xml");
        User user=(User)applicationContext.getBean("User");
        System.out.println(user);

    }
    @Test
    /*
    <!--将一个对象注入到另一个对象中-->
     */
    public void demo2(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("properties.xml");
        Student student=(Student)applicationContext.getBean("Student");
        System.out.println(student);
    }

}
