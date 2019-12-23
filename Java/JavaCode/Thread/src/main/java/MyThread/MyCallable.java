package MyThread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public String call() throws Exception {
        return "ʵ��Callable�ӿ�";
    }

    public static void main(String[] args) {
        // �̳�Thread������Thread��ĳ���
        MyThread t1 = new MyThread();
        t1.start();
        Thread t1_1 = new Thread(t1, "t_1_1");
        Thread t1_2 = new Thread(t1, "t_1_2");
        Thread t1_3 = new Thread(t1, "t_1_3");
        t1_1.start();
        // ʵ��Runnable�ӿڡ���������ĳ���
        MyRunnable myRunnable = new MyRunnable();
        Thread t2 = new Thread(myRunnable, "t2");
        t2.start();
        // ʵ��Callable�ӿڡ����з���ֵ������
        MyCallable t3 = new MyCallable();
        try {
            String res = t3.call();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
