package singleton;


public class Singleton_1 {

    /**
     * �ڶ��߳���Ҳ�ǰ�ȫ���������صĶ�������
     * ����ʹ�����ʱ���Ѿ������󴴽����
     * ȱ�㣺�˷���Դ�����õ�ʱ��Ҳ�����˶���
     * ���ң���ĳЩ�������޷�ʹ�����ַ����ģ�
     */
    private static final Singleton_1 instance = new Singleton_1();

    private Singleton_1() {

    }

    public static Singleton_1 getInstance() {
        return instance;
    }
}
