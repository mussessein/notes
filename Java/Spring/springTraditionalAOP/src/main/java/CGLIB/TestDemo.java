package CGLIB;

import org.junit.Test;

public class TestDemo {
    @Test
    public void demo() {
        Product product = new Product();
        //产生代理类
        Product proxy = (Product) new MyCGLIBProxy(product).createProxy();
        proxy.save();
        proxy.find();
    }
}
