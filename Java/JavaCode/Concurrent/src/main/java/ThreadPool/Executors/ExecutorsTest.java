package ThreadPool.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
通过线程池来执行线程，
通过结果可以发现，线程的名字前多了pool，说明是使用线程池来执行Runnable任务的
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        // 创建一个具有固定线程数6的线程池
        ExecutorService pool = Executors.newFixedThreadPool(6);
        // 使用lambda创建Runnable对象
        Runnable target = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "---i值：" + i);
            }
        };
        // 向线程池提交两个线程
        pool.submit(target);
        pool.submit(target);
        //关闭线程池
        pool.shutdown();
    }
}
