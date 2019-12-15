package Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ������ʵ��
 * ��д���У����Խ����������������
 * �ڶ����У��޷�����д������������
 */
public class Write_Read {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public static void main(String[] args) {

        Runnable task = () -> {
            try {
                lock.writeLock().lock();
                System.out.println("������д��");
                lock.readLock().lock();
                System.out.println("�����˶���");
            } finally {
                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
