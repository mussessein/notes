package AspectJDemo1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/*

切面类 注解方式
@Aspect切面的标识
 */
@Aspect
public class MyAspectJAnnotation {

    //* 任意返回值 报名.类名.方法名（*表示所有方法） （..）表示任意参数
   //@Before(value="execution(* AspectJDemo1.ProductDao.*(..))")
    //仅在save方法增强
    @Before(value = "pointcut_1()")
    public void before(){

        System.out.println("**************前置通知**************");
    }

    //如果某方法有返回值，后置通知需要拿到返回值，需要这么写后置通知
    @AfterReturning(value = "execution(* AspectJDemo1.ProductDao.update())",returning = "hi")
    public void AfterReturning(Object hi){

        //输出返回值
        System.out.println("**************后置通知**************"+hi);
    }

    //Around的返回值就是目标代理方法执行的返回值
    //可以完全拦截目标方法
    @Around(value = "execution(* AspectJDemo1.ProductDao.findAll()))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("**************环绕前通知**************");
        Object object=joinPoint.proceed();

        System.out.println("**************环绕后通知**************");
        return object;
    }

    //异常抛出通知
    @AfterThrowing(value = "execution(* AspectJDemo1.ProductDao.findOne()))",throwing = "e")
    public void AfterThrowing(Throwable e){
        System.out.println("**************异常抛出通知**************"+e);
    }


    //最终通知
    @After(value = "execution(* AspectJDemo1.ProductDao.delete()))")
    public void after(){
        System.out.println("**************最终通知**************");
    }

    //为切点重新命名，此切点就可以直接用方法名pointcut_1代替
    @Pointcut(value="execution(* AspectJDemo1.ProductDao.save(..))")
    public void pointcut_1(){ }





}
