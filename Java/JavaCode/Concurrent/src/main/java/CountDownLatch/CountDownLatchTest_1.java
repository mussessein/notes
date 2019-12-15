package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch的两种应用场景：(不能实现多个线程顺序执行！)
 * 1. 让某线程等待其他线程执行完毕，再开始执行；
 * 2. 让多个线程同时开始并行执行；
 */
public class CountDownLatchTest_1 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(3);
        Task task_A = new Task(latch, "A");
        Task task_B = new Task(latch, "B");
        Task task_C = new Task(latch, "C");
        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 1000; i++) {
//            Task task = new Task(latch, "" + i);
//            executorService.submit(task);
//        }
        executorService.submit(task_A);
        executorService.submit(task_B);
        executorService.submit(task_C);
        executorService.shutdown();
        // 挂起mian线程，等待计数器置0，也就是三个线程执行完毕。
        latch.await();
        System.out.println(Thread.currentThread().getName());

    }
}