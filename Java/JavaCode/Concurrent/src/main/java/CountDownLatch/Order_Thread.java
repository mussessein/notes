package CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����hello
 * ִ�������߳�,˳���ӡ_A,_B,_C
 * ������hello_A_B_C
 */
public class Order_Thread {

    public static void main(String[] args) throws InterruptedException {

        // ���̳߳�,ִ����һ������,���������һ������
        ExecutorService executor = Executors.newSingleThreadExecutor();
        StringBuilder stringBuilder = new StringBuilder("hello");
        //CountDownLatch latch = new CountDownLatch(3);
        executor.submit(() -> {
            stringBuilder.append("_A");
            //latch.countDown();
        });
        executor.submit(() -> {
            stringBuilder.append("_B");
            //latch.countDown();
        });
        executor.submit(() -> {
            stringBuilder.append("_C");
            //latch.countDown();
        });
        executor.shutdown();
        //latch.await();
        System.out.println(stringBuilder);
//        if (Thread.activeCount() == 2) {   // һ�����߳�,һ�����������߳�,
//            System.out.println(stringBuilder);
//        }
    }
}