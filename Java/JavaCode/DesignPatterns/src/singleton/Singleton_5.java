package singleton;

/**
 * ʹ�þ�̬�ڲ���ʵ��
 * Ҳ��ͨ�������أ�ʵ�ֵģ�
 */
public class Singleton_5 {
    private Singleton_5() {
    }

    private static class Inner {

        private static Singleton_5 s = new Singleton_5();
    }

    // ��û�е���getInstance��ʱ���ڲ��಻�����
    private static Singleton_5 getInstance() {
        return Inner.s;
    }
}
