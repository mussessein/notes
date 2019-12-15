package singleton;

/**
 * �����أ��� �õ�ʱ��ż��أ������˷��ڴ�
 * ʵ����������,��������ģʽ
 * ��ʹ�����ʱ�򣬲����ж���Ĵ�����
 * ֻ�е�����getInstance����֮�󣬶���ű�����
 * �˷����ڶ��߳���,�޷���������ģʽ
 *
 * ���volatile��synchronized���������̰߳�ȫ
 *
 */
public class Singleton_2 {

    private static volatile Singleton_2 instance; // ��һ������أ�instanceָ��null

    private Singleton_2(){

    }

    public static synchronized Singleton_2 getInstance(){

        if (null == instance)
            instance=new Singleton_2();

        return Singleton_2.instance;
    }

}
