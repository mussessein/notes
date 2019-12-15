package AspectJXML;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspectXML {


    //前置通知,joinPoint得到相应的信息
    public void before(JoinPoint joinPoint){

        System.out.println("************XML前置通知************"+joinPoint);

    }

    //后置通知，获得方法的返回值
    public void afterReturning(Object hi){

        System.out.println("************XML后置通知************"+hi);
    }

    //环绕通知
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("************XML环绕前通知************");

        Object object=joinPoint.proceed();

        System.out.println("************XML环绕后通知************");

        return object;
    }

    //异常抛出通知
    public void afterThrowing(Throwable e){
        System.out.println("************XML异常抛出通知************"+e);
    }

    //最终通知
    public void after(){
        System.out.println("************XML最终通知************");

    }


}