package JDK;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
模拟代理的实现过程，只能代理实现了接口的方法
 */
public class MyJDKProxy implements InvocationHandler {
    private UserDao userDao;

    public MyJDKProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    //创建一个UserDao的代理类，三个参数（类加载器反射获得，此类实现的接口，InvocationHandler接口）
    public Object createProxy() {
        Object proxy = Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), this);
        return proxy;
    }

    @Override
    //三个参数（代理对象，增强方法，方法参数）
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强的方法：
        //先判断是否是需要增强的方法，然后进行增强校验
        if ("save".equals(method.getName())) {
            System.out.println("JDK权限校验...");
            //返回的是原方法的执行。反射方式的方法执行
            return method.invoke(userDao, args);
        } else {
            return method.invoke(userDao, args);
        }
    }
}
