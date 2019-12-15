import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����sleep�Ƿ����cpu��
 * ���Եõ�sleep����cpu
 */
public class Test implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100000000; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
            if (i==99099) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(10000);
        // ʹ��lambda����Runnable����
        Runnable target = new Test();
        // ���̳߳��ύ�����߳�
//        for (int i = 0; i < 100; i++) {
//            pool.submit(target);
//        }
        //�ر��̳߳�
        pool.submit(target);

        pool.shutdown();
    }
}
