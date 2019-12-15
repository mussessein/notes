package singleton;

/**
 * double check��ʽ(�ֲ����ͬ������)
 * ��������ܵ�ͬ������ģʽ
 * ���� ��һ������:
 * ����������ָ���쳣---->ʹ��volatile���Խ��
 * Ϊʲô���ָ�룿
 * ��Ϊ��ʹ��volatile���п��ܷ���ָ��������
 * ������˳��
 * 1.Ϊinstance�����ڴ�ռ�
 * 2.��ʼ��instance
 * 3.��instanceָ�������ڴ��ַ
 * ������󣺿��ܱ��1->3->2
 * ��û��ʼ������ִ���˵�3�����ͻᷢ����ָ���쳣
 * ����ʹ����volatile�ؼ���֮�󣬾Ϳ��Խ�ָֹ��������
 * �������� JVM ����ָ�����ŵ����ԣ�ִ��˳���п��ܱ�� 1->3->2��
 * ָ�������ڵ��̻߳����²���������⣬�����ڶ��̻߳����»ᵼ��һ���̻߳�û�û�г�ʼ����ʵ����
 * ���磬�߳� T1 ִ���� 1 �� 3����ʱ T2 ���� getUniqueInstance() ���� uniqueInstance ��Ϊ�գ�
 * ��˷��� uniqueInstance������ʱ uniqueInstance ��δ����ʼ����
 */
public class Singleton_4 {
    private volatile static Singleton_4 instance;

    private Singleton_4() {

    }

    // double check��ʽ
    public static Singleton_4 getInstance() {
        /**
         *  ��һ���жϣ���Ҫ���ǵ����������⣬
         *  ����������֮���Ժ��ÿ���ٵ��ô˷�����
         *  ����Ҫ����synchronized�жϣ�ֱ�ӷ���instance
         */
        if (null == instance)
        /**
         * synchronizedֻ�����һ�μ���
         * ������instance����û�д�����ʱ��,null==instance
         * Ȼ��,��������instance����,Ȼ���ͷ���
         * ��һ���߳̽����ж�,instance!=null
         * �����ٽ���synchronized����,ֱ��return Singleton_4.instance;
         */
            synchronized (Singleton_4.class) {
                if (null == instance)     // �ڶ��μ��ʹ����������ͬ�� Singleton �����Ϊ�����ܡ�
                    instance = new Singleton_4();
            }

        return Singleton_4.instance;
    }
}
