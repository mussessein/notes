package StrongMethod;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class OverrideBeanPostProcessor implements BeanPostProcessor {


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {

        //设置一个代理对象，实现代理方法,进行原方法的增强！！！
        //先判断bean是否为此类
        if ("UserDao".equals(beanName)){
            Object proxy=Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    //判断是否为要增强的方法
                    if ("save".equals(method.getName())){
                        //进行增强
                        System.out.println("这里是代理方法...");
                        return method.invoke(bean,args);
                    }else {
                        return method.invoke(bean, args);
                    }
                }
            });
            return proxy; //rerurn一个代理对象
        }else {
            return bean;
        }
    }
}
