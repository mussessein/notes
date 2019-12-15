package JDK;

import org.junit.Test;

public class TestDemo {
    @Test
    public void demo(){

        UserDao userDao=new UserDaoImpl();
        UserDao proxy=(UserDao)new MyJDKProxy(userDao).createProxy();
        proxy.save();
        proxy.update();
    }
}
