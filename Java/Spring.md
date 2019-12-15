# Spring

Spring官方GitHub项目：

所有代码都可以在./Java/spring目录中查看

### IOC（Inverse of Control 控制反转）和DI（依赖注入）

- IOC：原本需要手动创建的对象（New），交给了Spring框架管理，不需要关心对象是如何创建的，只是从Spring容器中获取需要的对象即可，即反转。
- DI：将对象属性（在下面的例子中就是name属性），注入给实例的过程就是依赖注入

#### 传统IOC的两个演示

演示1：

首先创建一个接口和一个JavaBean来实现此接口：

```java
public interface UserService {
    public void sayHello();
}
public class UserServiceImpl implements UserService{
    private String name;
    // 省略getter/setter方法
    @Override
    public void sayHello() {
        System.out.println("Hello Spring!"+name);
    }
}
```

创建一个applicationContext.xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
    <bean id="UserService" class="ioc.UserServiceImpl">			<!--IOC-->
        <property name="name" value="zhangsan"></property>		<!--DI注入属性-->
    </bean>
</beans>
```

添加一个测试方法：

```java
// 传统开发：
@Test
public void demo_1(){
    UserServiceImpl us=new UserServiceImpl();
    us.setName("zhang");
    us.sayHello();
}
// IOC方式，从Spring那里获取对象，将对象属性设置给对象的过程即依赖注入
@Test
public void demo_2(){
    //创建Spring工厂
    ApplicationContext applicationContext=
        new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService us=(UserService) applicationContext.getBean("UserService");
    us.sayHello();
}
```



演示2：在一个对象中注入另一个对象

两个JavaBean：

```java
public class Cat {
    private String name;
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
    // setter/getter...
}

public class Student {
    private String name;
    private Integer age;
    // 对象属性
    private Cat cat;
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cat=" + cat +
                '}';
    }
	// setter/getter...
}
```

xml配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    
    <!--P命名空间需要加入xmlns:p="http://www.springframework.org/schema/p"-->
    <bean id="Student" class="properties.Student" p:cat-ref="Cat">
        <property name="name" value="小李"/>
        <property name="age" value="13"/>
        <!--<property name="cat" ref="Cat"/>-->
    </bean>
    <!--使用P命名空间来对属性进行赋值-->
    <bean id="Cat" class="properties.Cat" p:name="Kitty"/>
</beans>
```

测试方法：

```java
@Test

public void demo2(){
    ApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("properties.xml");
    Student student=(Student)applicationContext.getBean("Student");
    System.out.println(student);
}
```

运行结果：

Student{name='小李', age=13, cat=Cat{name='Kitty'}}

**注入对象属性的的方法：**

- P命名空间：`    <bean id = "Student" class = "properties.Student" p:cat-ref = "Cat">`

  当然，设置对象属性，也可以直接用P命名空间 p：key=“value”

- property标签： ` <property name = "cat" ref = "Cat"/>`

- SqEL注入：`value ="#{'13'}" `



#### 注解IOC

参考：<https://www.jianshu.com/p/5c20850d2e45>

为了简化xml，通过注解来装配Bean

详见：SpringMVC.md



### AOP（Aspect Oriented Program 面向切面编程）

在此之前先了解SpringAOP的底层实现方式：传统动态代理CGLib和JDK动态代理。

- JDK动态代理：主要使用的是java.lang.reflect包下的两个类Proxy和InvocationHandler。InvocationHandler接口通过横切面逻辑和反射机制，调用目标类的代码，动态地将横切逻辑和目标类的业务逻辑进行编织，实现动态代理。
- CGLIb动态代理：采用底层字节码技术，为目标类生成一个子类，在子类中拦截父类的方法，进行横切逻辑的织入。**相当于是重写父类方法。**

区别：

1. JDK动态代理是面向接口的代理模式，只能代理实现了接口的类。（Spring的默认代理方式）
2. CGLIB可以实现在运行期动态扩展java类。目标类不可以被final修饰，否则代理失败。因为final类不可以被继承。
3. CGLIB的性能要比JDK高很多！但是，CGLIB创建对象花费时间要更多！所以：如果是代理单例对象，或者代理具有实例池的类，无需频繁创建对象，CGLIB更有优势。反之，如果要频繁创建对象，JDK更有优势。在JDK1.8以后，JDK动态代理的性能已经有很大提高！

#### JDK动态代理

1. 首先创建接口和实现类：

```java
// 我们定义两个方法，并进行实现
public interface UserDao {
    public void save();
    public void update();
}
public class UserDaoImpl implements UserDao {
    @Override
    public void save() {
        System.out.println("保存用户...");
    }
    @Override
    public void update() {
        System.out.println("更新用户...");
    }
}
```

2. 创建代理类

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*
JDK代理的实现过程，只能代理实现了接口的方法
 */
public class MyJDKProxy implements InvocationHandler {
    private UserDao userDao;
    public MyJDKProxy(UserDao userDao) {
        this.userDao = userDao;
    }
    //创建一个UserDao的代理类，三个参数（类加载器反射获得，此类实现的接口，InvocationHandler接口）
    public Object createProxy() {
        Object proxy = Proxy.newProxyInstance(userDao.getClass().getClassLoader(), userDao.getClass().getInterfaces(), this);
        return proxy;
    }
    @Override
    //三个参数（代理对象，增强方法，方法参数）
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 判断需要增强的方法
        if ("save".equals(method.getName())) {
            System.out.println("JDK权限校验...");
            //返回的是原方法的执行。反射方式的方法执行
            return method.invoke(userDao, args);
        } else {
            return method.invoke(userDao, args);
        }
    }
}
```

3. 测试一下

```java
import org.junit.Test;
public class TestDemo {
    @Test
    public void demo(){
        UserDao userDao=new UserDaoImpl();
        // 创建代理类
        UserDao proxy=(UserDao)new MyJDKProxy(userDao).createProxy();
        proxy.save();
        proxy.update();
    }
}
```

执行结果：

```
JDK权限校验...
保存用户...
更新用户...
```

#### CGLIB动态代理

1. 不需要创建接口，直接创建目标类

```java
package CGLIB;
public class Product {
    public void save() {
        System.out.println("保存商品...");
    }
    public void update() {
        System.out.println("更新商品...");
    }
}
```

2. 创建代理类

```java
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;
public class MyCGLIBProxy implements MethodInterceptor {
    private Product product;
    public MyCGLIBProxy(Product product) {
        this.product = product;
    }
    public Object createProxy() {
        /**
         * 1.核心类Enhancer
         * 2.设置目标类为父类
         * 3.将此代理类回调
         * 4.生成代理类，并返回
         */
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(product.getClass());
        enhancer.setCallback(this);
        Object proxy = enhancer.create();
        return proxy;
    }
    @Override
    //四个个参数（代理对象，要增强的方法，方法参数，方法代理对象）
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("save".equals(method.getName())) {
            System.out.println("CGLIB权限校验...");
            return methodProxy.invokeSuper(proxy, args);
        } else {
            return methodProxy.invokeSuper(proxy, args);
        }
    }
}
```

3. 测试

```java
import org.junit.Test;
public class TestDemo {
    @Test
    public void demo() {
        Product product = new Product();
        //产生代理类
        Product proxy = (Product) new MyCGLIBProxy(product).createProxy();
        proxy.save();
        proxy.find();
    }
}
```

运行结果：

```
CGLIB权限校验...
保存商品...
找到商品...
```



#### SpringAOP

使用Spring之后，就不需要自己写代理类。只需要写方法的具体增强内容，将关注点放在业务上，而不是实现上。提高开发效率。

SpringAOP是动态织入。

先来了解一些AOP的概念：

1. **切面（aspect）**：我们要增强的横切逻辑的编写的类，就是切面。简单说是注解@Aspect下的类就是切面。
2. **连接点（JoinPoint）**：我们进行方法增强，会在方法的前，后，抛出异常时等等多个方面进行增强，这个就是连接点。一般叫做：前置增强，后置增强，环绕增强.....
3. **切入点（PointCut）**：即拦截的方法，一个拦截的方法（切入点）可能有多个连接点，即多个增强。
4. **通知（Advice）**：即方法增强的内容。我们要如何增强此方法，又叫添加通知。

演示AOP自动代理：

1. 目标类

```java
public class CustomerDao {
    public void save() {
        System.out.println("客户保存...");
    }
    public void update() {
        System.out.println("客户更新...");
    }
}
```

2. 切面类

```java
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
```

3. applicationContext_4.xml配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--配置目标类-->
    <bean id="customerDao" class="aopDemoAuto2.CustomerDao"/>
    <bean id="myBeforeAdvice" class="aopDemoAuto2.MyBeforeAdvice"/>
    
    <!--在类生成的过程中，就自动产生代理-->
    <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
    
    <!--配置一个切面-->
    <bean id="myAdvisor" class="org.springframework.aop.support.RegexpMethodPointcutAdvisor">
        <!--定位需要增强的方法，需要转译\.-->
        <property name="pattern" value="aopDemoAuto2\.CustomerDao\.save"/>
        <!--定位切面-->
        <property name="advice" ref="myAroundAdvice"/>
    </bean>
</beans>
```

4. 测试

```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_4.xml")
public class TestDemo {

    @Resource(name="customerDao")
    private CustomerDao customerDao;
    @Test
    public void demo(){
        customerDao.save();
        customerDao.update();
    }
}
```

运行结果：

保存方法实现了增强

```
环绕前增强====================
客户保存...
环绕后增强====================
客户更新...
```



#### Spring AspectJ

与SpringAOP略微不同，AspectJ是静态织入

静态织入：指在编译时期就织入，即：编译出来的class文件，字节码就已经被织入了。

动态织入：根据代码动态运行的中间状态来决定如何操作，每次调用target的时候都执行。

##### 注解方式实现：

1. 首先目标类

```java
public class ProductDao {
    public void save(){
        System.out.println("保存商品...");
    }
    // 用于抛出异常的通知
    public void findOne(){
        System.out.println("查询一个商品...");
        int i = 1/0;	// 这里会抛出异常 
    }
}
```

2. 切面类

有很多种通知，这里列出两种。

execution(* AspectJDemo1.ProductDao.save(..))

execution定位切入点。

```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
@Aspect
public class MyAspectJAnnotation {
    // 前置通知
	@Before(value="execution(* AspectJDemo1.ProductDao.save(..))")
    public void before(){
        System.out.println("**************前置通知**************");
    }
    //异常抛出通知
    @AfterThrowing(value = "execution(* AspectJDemo1.ProductDao.findOne()))",throwing = "e")
    public void AfterThrowing(Throwable e){
        System.out.println("**************异常抛出通知**************"+e);
    }
}
```

3. 配置文件applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop" xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--开启AspectJ的注解开发，自动代理-->
    <aop:aspectj-autoproxy/>
    <!--目标类-->
    <bean id="productDao" class="AspectJDemo1.ProductDao"/>
    <!--定义切面-->
    <bean class="AspectJDemo1.MyAspectJAnnotation"/>
</beans>
```

4. 测试一哈

```java
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
        productDao.findOne();
        System.out.println("-------------------------------------------------------");
    }
}
```

运行结果：

在执行过findOne方法之后，抛出异常

```
**************前置通知**************
保存商品...
查询一个商品...
**************异常抛出通知**************java.lang.ArithmeticException: / by zero
java.lang.ArithmeticException: / by zero
	at AspectJDemo1.ProductDao.findOne(ProductDao.java:22)
```

xml的AspectJ实现就不写了，具体可以参考代码

./Java/spring/springAspectJ
