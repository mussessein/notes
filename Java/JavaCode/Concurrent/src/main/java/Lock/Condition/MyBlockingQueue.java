package Lock.Condition;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��Condition��ʵ�ּ򵥶���
 */
public class MyBlockingQueue<T> {
    private int limit;
    private final Lock lock = new ReentrantLock();
    private final Condition Full = lock.newCondition();
    private final Condition Empty = lock.newCondition();
    private List<T> queue = new LinkedList<>();

    public MyBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void enqueue(T item) throws InterruptedException {
        lock.lock();
        try {
            // ������
            while (queue.size() == limit) {
                Full.await();
            }
            queue.add(item);
            Empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T dequeue(T item) throws InterruptedException {
        lock.lock();
        try {
            // ���п�
            while (queue.size() == 0) {
                Empty.await();
            }
            Full.signal();
            return queue.remove(0);
        } finally {
            lock.unlock();
        }
    }
}
