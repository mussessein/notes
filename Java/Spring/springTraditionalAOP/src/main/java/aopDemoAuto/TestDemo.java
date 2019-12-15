package aopDemoAuto;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;


/*
在类生成的过程中，就自动产生代理
<property name="beanNames" value="*Dao"/>
配置好所有的Bean之后，自动拿到所有的Bean，进行代理
所有方法，进行前置增强
 <property name="interceptorNames" value="myBeforeAdvice"/>

 ==================还是不能进行单个方法的增强================
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_3.xml")
public class TestDemo {

    @Resource(name="studentDao")
    private StudentDao studentDao;
    @Resource(name="customerDao")
    private CustomerDao customerDao;

    @Test
    public void demo(){
        customerDao.savefind();
        customerDao.save();
        customerDao.update();
        customerDao.delete();
        //
        studentDao.find();
        studentDao.save();
        studentDao.update();
        studentDao.delete();
    }

}
