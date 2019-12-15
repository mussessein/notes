package Lock.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替实现，+1，,-1，
 * 最后结果为0
 */
public class test {
    private static Lock lock = new ReentrantLock();
    private static Condition subCond = lock.newCondition();
    private static Condition addCond = lock.newCondition();
    private static int count = 0;

    public static void add() {
        lock.lock();
        try {
            while (count == 1)
                addCond.await();
            count++;
            subCond.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void sub() {
        lock.lock();
        try {
            while (count == 0)
                subCond.await();
            count--;
            addCond.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                add();
            }
        }, "add").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sub();
            }
        }, "add").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(count);
        }
    }
}
