package aopDemo1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
/*
使用普通Advice作为切面，将对目标的所有方法进行拦截，不够灵活，实际开发，
比较常用带有切点的切面
见demo2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_1.xml")  //声明使用的配置文件
public class TestDemo {

    //注入实现类
//    @Resource(name="studentDao")

    //注入代理类,直接可以进行增强
    @Resource(name="studentDaoProxy")
    private StudentDao studentDao;

    @Test
    public void demo(){

        studentDao.find();
        studentDao.save();
        studentDao.update();
        studentDao.delete();
    }
}
