package CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 场景2
 */
public class CountDownLatchTest_2 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        Task task_A = new Task(latch, "A");
        Task task_B = new Task(latch, "B");
        Task task_C = new Task(latch, "C");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(task_A);
        executorService.submit(task_B);
        executorService.submit(task_C);
        executorService.shutdown();
        // 挂起mian线程，等待计数器置0，也就是三个线程执行完毕。
        latch.countDown();
        System.out.println(Thread.currentThread().getName());
    }
}
