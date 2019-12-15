package Reflect;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldTest {

    /**
     * 通过Field类，来获得对象的属性
     * 1.获得共有属性
     * 2.获得私有属性
     */
    @Test //此方法用于获得public属性
    public void demo_1() throws Exception{
        //获得Class
        Class class_1=Class.forName("Reflect.Person");
        //获得属性
        Field field = class_1.getField("name");
        //对获得的属性进行操作，赋值
        Person p=(Person)class_1.getConstructor().newInstance();
        field.set(p,"李四");

        System.out.println(p);
        //用field.get得到的是此对象在field方法下得到的属性
        Object object = field.get(p);
        System.out.println(object);

    }

    @Test //获得private属性！！！
    public void demo_2()throws Exception{
        //获得Class
        Class class_1=Class.forName("Reflect.Person");
        //获得私有属性，用getDeclaredField
        Field field = class_1.getDeclaredField("sex");
        //操作获得的属性
        Person p = (Person) class_1.getConstructor().newInstance();
        //针对私有属性，需要设置一个可以访问的权限
        field.setAccessible(true);
        field.set(p,"男");
        //直接输出，表示打印对象
        System.out.println(p);
        //用field.get得到的是此对象在field方法下得到的属性
        Object object = field.get(p);
        System.out.println(object);
    }
}
