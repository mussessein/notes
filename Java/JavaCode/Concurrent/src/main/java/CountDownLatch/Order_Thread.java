package CountDownLatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 输入hello
 * 执行三个线程,顺序打印_A,_B,_C
 * 最后输出hello_A_B_C
 */
public class Order_Thread {

    public static void main(String[] args) throws InterruptedException {

        // 单线程池,执行完一个任务,才能添加下一个任务
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
//        if (Thread.activeCount() == 2) {   // 一个主线程,一个垃圾回收线程,
//            System.out.println(stringBuilder);
//        }
    }
}