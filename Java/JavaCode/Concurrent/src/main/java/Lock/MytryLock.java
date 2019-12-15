package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MytryLock {

    private static final Lock lock = new ReentrantLock();
    // �����Ƿ�������ȷ�����������ñ�־λ�ж�
    private static boolean isLocked = false;

    public static void test() {
        try {
            // �����������ȴ�1000ms
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                // trylock����true�����õ���
                isLocked = true;
                System.out.println(Thread.currentThread().getName() + "�õ�����");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                /**
                 * û�õ������������̼߳����������
                 * ��������
                 */
                System.out.println(Thread.currentThread().getName() + "û�õ�����");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (isLocked == true)
                lock.unlock();
        }
    }

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + "�߳�����");
            try {
                Thread.sleep(1000);
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);

        Thread[] threads = {t1, t2, t3, t4};
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
