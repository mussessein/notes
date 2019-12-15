package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 比如：线程A，B拿同一把锁
 * A拿到锁，B一直在等待
 * 如何能让B不再等待，即打断B的等待？
 * 用普通的lock.lock()是无法打断的
 * 用lock.lockInterruptibly();就可以被打断，不再等待
 */
public class LockInterruptibly {
    private static final Lock lock = new ReentrantLock();
    static boolean flag = false;

    public static void test() {
        String name = Thread.currentThread().getName();
        try {
            System.out.println(name + " 开始获取锁");
            lock.lockInterruptibly();
            System.out.println(name + " 得到锁");
            System.out.println(name + " 开工干活");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(name + " : " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " 被中断");
            System.out.println(name + " 做些别的事情");
        } finally {
            try {
                lock.unlock();
                System.out.println(name + " 释放锁");
            } catch (Exception e) {
                System.out.println(name + " : 没有得到锁的线程运行结束");
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
