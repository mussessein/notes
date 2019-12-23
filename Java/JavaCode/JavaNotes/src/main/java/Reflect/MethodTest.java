package Reflect;



import java.lang.reflect.Method;

/**
 * ͨ��Method�࣬����ö���ķ���
 * 1.��ù��з���
 * 2.���˽�з���
 */
public class MethodTest {
    //���Թ��з�����
    public void demo_1()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //ʵ����
        Person person =(Person)class_1.getConstructor().newInstance();
        //��ù��з���
        Method method =class_1.getMethod("eat");
        // ����һ����������,��ִ��method�õ��ķ���
        method.invoke(person);
    }


    //����˽�з�����
    public void demo_2()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //ʵ����
        Person person =(Person)class_1.getConstructor().newInstance();
        //���˽�з���
        Method method=class_1.getDeclaredMethod("shower");
        //����˽�з����ķ���Ȩ��
        method.setAccessible(true);
        //ִ�з���,invoke(����,�˷����Ĳ���)
        method.invoke(person, (Object) null);
    }

    //���Ժ��κͺ��з���ֵ�ķ�����
    public void demo_3()throws Exception{
        Class class_1 = Class.forName("Reflect.Person");
        //ʵ����
        Person person =(Person)class_1.getConstructor().newInstance();
        //���˽�з���
        Method method=class_1.getDeclaredMethod("speak", String.class);
        ////����˽�з����ķ���Ȩ��
        method.setAccessible(true);
        // speak����,��Ҫ����һ��String,����һ��String
        // invoke����ͳһ����object
        Object object=method.invoke(person,"hello");
        System.out.println(object);
    }
}
