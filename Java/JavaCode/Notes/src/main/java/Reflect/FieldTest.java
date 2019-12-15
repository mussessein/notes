package Reflect;

import org.junit.Test;

import java.lang.reflect.Field;

public class FieldTest {

    /**
     * ͨ��Field�࣬����ö��������
     * 1.��ù�������
     * 2.���˽������
     */
    @Test //�˷������ڻ��public����
    public void demo_1() throws Exception{
        //���Class
        Class class_1=Class.forName("Reflect.Person");
        //�������
        Field field = class_1.getField("name");
        //�Ի�õ����Խ��в�������ֵ
        Person p=(Person)class_1.getConstructor().newInstance();
        field.set(p,"����");

        System.out.println(p);
        //��field.get�õ����Ǵ˶�����field�����µõ�������
        Object object = field.get(p);
        System.out.println(object);

    }

    @Test //���private���ԣ�����
    public void demo_2()throws Exception{
        //���Class
        Class class_1=Class.forName("Reflect.Person");
        //���˽�����ԣ���getDeclaredField
        Field field = class_1.getDeclaredField("sex");
        //������õ�����
        Person p = (Person) class_1.getConstructor().newInstance();
        //���˽�����ԣ���Ҫ����һ�����Է��ʵ�Ȩ��
        field.setAccessible(true);
        field.set(p,"��");
        //ֱ���������ʾ��ӡ����
        System.out.println(p);
        //��field.get�õ����Ǵ˶�����field�����µõ�������
        Object object = field.get(p);
        System.out.println(object);
    }
}
