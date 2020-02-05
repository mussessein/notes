package Synchronizer.Semaphore;
import java.util.concurrent.*;

/**
 * ��������ͨ·���½��߸��̣߳��۲����н��
 * Semaphore�ź�����������ͬʱ���е��߳�������
 */
public class SemaphoreTest {
    private static final Semaphore semaphore=new Semaphore(3);
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    private static class InformationThread extends Thread{
        private final String name;
        public InformationThread(String name)
        {
            this.name=name;
        }
        @Override
        public void run()
        {
            try
            {
                Thread.sleep(1000);
                semaphore.acquire();
                System.out.println(
                        Thread.currentThread().getName()+" �õ�ͨ·");
                System.out.println("��ǰͨ·������"+semaphore.availablePermits());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName()+" �Ѿ��ͷ�ͨ·");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args)
    {
        String[] name= {"A","B","C","D","E","F","G"};
        for(int i=0;i<7;i++)
        {
            Thread t1=new InformationThread(name[i]);
            EXECUTOR .submit(t1);
        }
        EXECUTOR .shutdown();
    }

}
