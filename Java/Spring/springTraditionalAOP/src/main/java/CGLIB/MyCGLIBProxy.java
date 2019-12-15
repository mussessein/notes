package CGLIB;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyCGLIBProxy implements MethodInterceptor {

    private Product product;

    public MyCGLIBProxy(Product product) {
        this.product = product;
    }

    public Object createProxy() {
        /**
         * 1.核心类Enhancer
         * 2.设置目标类为父类
         * 3.将此代理类回调
         * 4.生成代理类，并返回
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(product.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }

    @Override
    //四个个参数（代理对象，要增强的方法，方法参数，方法代理对象）
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("save".equals(method.getName())) {
            System.out.println("CGLIB权限校验...");
            return methodProxy.invokeSuper(proxy, args);
        } else {
            return methodProxy.invokeSuper(proxy, args);
        }
    }
}
