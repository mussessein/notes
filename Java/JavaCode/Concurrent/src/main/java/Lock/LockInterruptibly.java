package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ���磺�߳�A��B��ͬһ����
 * A�õ�����Bһֱ�ڵȴ�
 * �������B���ٵȴ��������B�ĵȴ���
 * ����ͨ��lock.lock()���޷���ϵ�
 * ��lock.lockInterruptibly();�Ϳ��Ա���ϣ����ٵȴ�
 */
public class LockInterruptibly {
    private static final Lock lock = new ReentrantLock();
    static boolean flag = false;

    public static void test() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " ��ʼ��ȡ��");
            lock.lockInterruptibly();
            System.out.println(name + " �õ���");
            System.out.println(name + " �����ɻ�");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(name + " : " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " ���ж�");
            System.out.println(name + " ��Щ�������");
        } finally {
            try {
                lock.unlock();
                System.out.println(name + " �ͷ���");
            } catch (Exception e) {
                System.out.println(name + " : û�еõ������߳����н���");
            }
        }

    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            test();
        }, "A");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(() -> {
            test();
        }, "B");
        t2.start();
        t2.interrupt();
    }
}
