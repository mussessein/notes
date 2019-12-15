package aopDemoAuto2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;


/*
在类生成的过程中，就自动产生代理
 ===============需要配置一个切面，进行方法的筛选================
  <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator">
 通过上面一段配置自动代理
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_4.xml")
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

        studentDao.find();
        studentDao.save();
        studentDao.update();
        studentDao.delete();
    }

}
