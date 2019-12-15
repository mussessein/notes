package AspectJXML;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class TestDemo {

    @Resource(name="customerDao")
    private CustomerDao customerDao;
    @Test
    public void demo(){
        customerDao.save();
        System.out.println("--------------------------------------------------------------------");

        customerDao.update();
        System.out.println("--------------------------------------------------------------------");

        customerDao.find();
        System.out.println("--------------------------------------------------------------------");

        customerDao.delete();
        System.out.println("--------------------------------------------------------------------");


    }

}
