package AspectJDemo1;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestDemo {

    @Resource(name="productDao")
    private ProductDao productDao;
    @Test
    public void demo(){
        productDao.save();
        System.out.println("-------------------------------------------------------");

        productDao.update();
        System.out.println("-------------------------------------------------------");

        productDao.findAll();
        System.out.println("-------------------------------------------------------");

        productDao.findOne();
        System.out.println("-------------------------------------------------------");

        productDao.delete();
    }



}
