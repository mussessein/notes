package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MytryLock {

    private static final Lock lock = new ReentrantLock();
    // 由于是否获得锁不确定，所以设置标志位判断
    private static boolean isLocked = false;

    public static void test() {
        try {
            // 尝试拿锁，等待1000ms
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                // trylock返回true，即拿到锁
                isLocked = true;
                System.out.println(Thread.currentThread().getName() + "拿到锁！");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                /**
                 * 没拿到锁，可以让线程继续做别的事
                 * 不会阻塞
                 */
                System.out.println(Thread.currentThread().getName() + "没拿到锁！");
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
            System.out.println(Thread.currentThread().getName() + "线程启动");
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
