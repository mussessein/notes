package Reflect;



import java.lang.reflect.Method;

/**
 * 通过Method类，来获得对象的方法
 * 1.获得公有方法
 * 2.获得私有方法
 */
public class MethodTest {
    //测试公有方法：
    public void demo_1()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //实例化
        Person person =(Person)class_1.getConstructor().newInstance();
        //获得公有方法
        Method method =class_1.getMethod("eat");
        // 传入一个对象引用,来执行method得到的方法
        method.invoke(person);
    }


    //测试私有方法：
    public void demo_2()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //实例化
        Person person =(Person)class_1.getConstructor().newInstance();
        //获得私有方法
        Method method=class_1.getDeclaredMethod("shower");
        //设置私有方法的访问权限
        method.setAccessible(true);
        //执行方法,invoke(对象,此方法的参数)
        method.invoke(person, (Object) null);
    }

    //测试含参和含有返回值的方法：
    public void demo_3()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //实例化
        Person person =(Person)class_1.getConstructor().newInstance();
        //获得私有方法
        Method method=class_1.getDeclaredMethod("speak", String.class);
        ////设置私有方法的访问权限
        method.setAccessible(true);
        // speak方法,需要传入一个String,返回一个String
        // invoke方法统一返回object
        Object object=method.invoke(person,"hello");
        System.out.println(object);
    }
}
