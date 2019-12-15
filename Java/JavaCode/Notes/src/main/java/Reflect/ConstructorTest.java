package Reflect;
import org.junit.Test;
import java.lang.reflect.Constructor;

/**
 *      ����õ�����Ĺ��췽������������ʵ����
 *      ��ô�������й��췽����
 *      Constuctor[] constructor=Class.forName("  ").getConstructors();
 *
 */
public class ConstructorTest {
    /**
     * ����޲εĹ��췽��
     */
    @Test
    public void demo_1() throws Exception {
        //�������ֽ����ļ���Ӧ�Ķ���
        Class class_1 = Class.forName("Reflect.Person");
        //��ù���������
        Constructor constructor=class_1.getConstructor();
        //ͨ�����������󣬴���ʵ�������൱��new һ��Person����
        Person person=(Person)constructor.newInstance();
        //�õ�personʵ���������䷽��
        person.eat();
    }

    /**
     * ��ú��ι��췽��
     * @throws Exception
     */
    @Test
    public void demo_2() throws Exception{
        //�������ֽ����ļ���Ӧ�Ķ���
        Class class_1 = Class.forName("Reflect.Person");
        Constructor constructor = class_1.getConstructor(String.class, String.class);
        Person person = (Person) constructor.newInstance("С��", "��");
        System.out.println(person);
    }
}
