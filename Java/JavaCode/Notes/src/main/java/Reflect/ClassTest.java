package Reflect;

import org.junit.Test;

/**
 * 反射获得对象
 */
public class ClassTest {
    /** 获得Class对象
     * 1.通过类名， 类.class
     * 2.通过对象，对象.getClass()
     * 3.Class.forName()  （用的最多！）
     */
    @Test
    public void demo_1() throws ClassNotFoundException {
        //1.通过类名.class的方式
        Class class_1=Person.class;

        //2.通过对象.getClass
        Person p1=new Person();
        Class class_2=p1.getClass();

        //通过forName,抛出异常
        Class class_3=Class.forName("Reflect.Person");
    }
}
