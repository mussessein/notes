package Lock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ��д��
 * ��������д����ռ��
 * ����һ�£��ڶ�������£�ReentrantReadWriteLock��synchronized��Ч��
 */
public class ReentrantReadWriteLock_Test {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void test_1(){
        System.out.println("start�� "+System.currentTimeMillis());

        try{
            lock.readLock().lock();

            // ��ȡ5��
            for (int i = 0; i < 5; i++) {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName()+" ���ڶ�ȡ....");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
            System.out.println("end�� "+System.currentTimeMillis());

        }
    }
    public static synchronized void test_2(){
        try{
            System.out.println("start�� "+System.currentTimeMillis());

            for (int i = 0; i < 5; i++) {
                Thread.sleep(20);
                System.out.println(Thread.currentThread().getName()+" ���ڶ�ȡ....");
            }
            System.out.println("end�� "+System.currentTimeMillis());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Runnable task_1 = ()->{
            test_1();
        };
        Runnable task_2 = ()->{
            test_2();
        };
        Thread t1 = new Thread(task_1);
        Thread t2 = new Thread(task_1);
//        Thread t1 = new Thread(task_2);
//        Thread t2 = new Thread(task_2);

        t1.start();
        t2.start();
    }
}
