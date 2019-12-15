package singleton;

/**
 * ö��ģʽ
 * ��Ȼ������ģʽ,��û�е���getInstance֮ǰ,�ڲ�ö���಻�����
 */
public class Singleton_6 {
    private Singleton_6() {
    }

    public static Singleton_6 getInstance() {
        System.out.println("��ʼ��ʵ��");
        return Singleton.INSTANCE.getInstance();
    }

    // ˽��ö����
    private enum Singleton {
        INSTANCE;

        // ������������
        private Singleton_6 singleton;

        // JVM��֤�����������ֻ����һ��
        Singleton() {
            singleton = new Singleton_6();
            System.out.println("ö�ٹ��캯��");
        }

        public Singleton_6 getInstance() {
            System.out.println("ö�ٷ��ص���");
            return singleton;
        }
    }

    public static void main(String[] args) {
        Singleton_6 demo = new Singleton_6();
        System.out.println("==============");
        Singleton_6 instance = getInstance();

    }
}
