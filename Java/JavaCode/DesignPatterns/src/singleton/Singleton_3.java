package singleton;

/**
 * �˷��������ã�
 */
public class Singleton_3 {
    private static Singleton_3 instance;

    private Singleton_3() {

    }

    public static Singleton_3 getInstance() {

        if (null == instance)
            /*
            ��Ȼ�����������ǵȵ���һ���߳�ִ����instance=new Singleton()���������ʱ��
            ��һ���߳��Ѿ�����if��䣬ͬ����ʵ��������һ��Singleton�����̲߳���ȫ��ԭ���3���ơ�
             */
            synchronized (Singleton_3.class) {
                instance = new Singleton_3();
            }
        return Singleton_3.instance;
    }
}
