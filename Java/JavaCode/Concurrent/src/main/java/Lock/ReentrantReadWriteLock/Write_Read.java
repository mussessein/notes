package Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级实现
 * 在写锁中，可以进入读锁（锁降级）
 * 在读锁中，无法进入写锁（锁升级）
 */
public class Write_Read {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {

        Runnable task = () -> {
            try {
                lock.writeLock().lock();
                System.out.println("进入了写锁");
                lock.readLock().lock();
                System.out.println("进入了读锁");
            } finally {
                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
