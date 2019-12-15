package aopDemoAuto;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/*
使用普通Advice作为切面，将对目标的所有方法进行拦截，不够灵活，实际开发，
比较常用带有切点的切面
见demo2
 */
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

        //没有过滤，所有方法都增强
        System.out.println("前置增强通知==================");

    }
}
