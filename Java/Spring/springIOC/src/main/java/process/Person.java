package process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class Person implements BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("第二步：设置了属性...");
        this.name = name;
    }

    public Person(){
        System.out.println("第一步：Person实例化...");
    }
    public void InitPerson(){
        System.out.println("第七步：对象初始化....");
    }
    public void destroyPerson(){
        System.out.println("第十一步：执行对象的销毁方法....");
    }
    public void run(){
        System.out.println("第九步：执行类内自己的方法....");
    }

    @Override
    /*
    这里可以看到bea实现的过程
     */
    public void setBeanName(String name) {
        System.out.println("第三步：设置Bean的名称（id）:"+name);
    }

    @Override
    /*
    这里了解工厂的信息
     */
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("第四步：了解工厂的信息...");
    }

    @Override
    /*

     */
    public void afterPropertiesSet() throws Exception {
        System.out.println("第六步：属性设置后执行...");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("第十步：执行spring的销毁...");
    }
}
