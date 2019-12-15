package aopDemo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

/*
增强的方法取决于 正则表达式
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_2.xml")
public class TestDemo {

    //没有代理的类
    //@Resource(name="customerDao")
    //使用代理
    @Resource(name="customerDaoProxy")
    private CustomerDao customerDao;
    @Test
    public void demo(){

        customerDao.savefind();
        customerDao.save();
        customerDao.update();
        customerDao.delete();


    }



}
