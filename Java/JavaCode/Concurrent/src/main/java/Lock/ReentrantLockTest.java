package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ʵ�ֿ�������
 */
public class ReentrantLockTest {
    private static final Lock lock = new ReentrantLock();

    public static void test1() {
        lock.lock();
        try {
            System.out.println("�ѽ���test_1");
            test2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void test2() {
        lock.lock();
        try {
            System.out.println("�ѽ���test_2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        test1();
    }
}
