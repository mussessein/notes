package Reflect;

import org.junit.Test;

/**
 * �����ö���
 */
public class ClassTest {
    /** ���Class����
     * 1.ͨ�������� ��.class
     * 2.ͨ�����󣬶���.getClass()
     * 3.Class.forName()  ���õ���࣡��
     */
    @Test
    public void demo_1() throws ClassNotFoundException {
        //1.ͨ������.class�ķ�ʽ
        Class class_1=Person.class;

        //2.ͨ������.getClass
        Person p1=new Person();
        Class class_2=p1.getClass();

        //ͨ��forName,�׳��쳣
        Class class_3=Class.forName("Reflect.Person");
    }
}
