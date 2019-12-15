package SqEl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OnSale {
    public double onsale(){
        //设置一个随机打折
        return Math.random()*199;
    }
}
