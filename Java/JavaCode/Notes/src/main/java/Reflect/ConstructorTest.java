package Reflect;
import org.junit.Test;
import java.lang.reflect.Constructor;

/**
 *      反射得到对象的构造方法，创建对象实例！
 *      获得此类的所有构造方法：
 *      Constuctor[] constructor=Class.forName("  ").getConstructors();
 *
 */
public class ConstructorTest {
    /**
     * 获得无参的构造方法
     */
    @Test
    public void demo_1() throws Exception {
        //获得类的字节码文件对应的对象
        Class class_1 = Class.forName("Reflect.Person");
        //获得构造器对象
        Constructor constructor=class_1.getConstructor();
        //通过构造器对象，创建实例对象，相当于new 一个Person对象
        Person person=(Person)constructor.newInstance();
        //得到person实例，调用其方法
        person.eat();
    }

    /**
     * 获得含参构造方法
     * @throws Exception
     */
    @Test
    public void demo_2() throws Exception{
        //获得类的字节码文件对应的对象
        Class class_1 = Class.forName("Reflect.Person");
        Constructor constructor = class_1.getConstructor(String.class, String.class);
        Person person = (Person) constructor.newInstance("小张", "男");
        System.out.println(person);
    }
}
