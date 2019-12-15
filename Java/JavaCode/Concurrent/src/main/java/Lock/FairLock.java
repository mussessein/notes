package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��ƽ����ʵ�֣�
 * �����е��̣߳��������õ�����
 * �ڹ�ƽ���£���һֱ�ǽ�������
 */
public class FairLock extends Thread {
    private static Lock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "---" + i);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock fairLock = new FairLock();
        Thread t1 = new Thread(fairLock, "A");
        Thread t2 = new Thread(fairLock, "B");
        t1.start();
        t2.start();
    }
}
