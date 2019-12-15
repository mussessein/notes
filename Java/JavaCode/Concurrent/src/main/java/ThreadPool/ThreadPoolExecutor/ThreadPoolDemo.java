package ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ThreadPoolExecutor的使用实例。
 * 四个必要参数,
 * RejectedHandler为自己定义的RejectedExecutionHandler实现类
 * 线程流程：
 * 1.进入核心池，核心池满了，之后线程，进入队列
 * 2.队列满了，继续创建线程，直到最大线程数
 * 3.最大线程数已满，拒绝后续线程
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * 设置线程池参数：
         * 核心线程：2
         * 最大线程：3
         * 阻塞队列大小：5
         * 拒绝策略：自定义
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 3, 2000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5), new MyRejectedHandler());

        // 启动一个主线程，内部启动10个子线程添加进线程池
        Runnable runTask = () -> {
            for (int i = 0; i < 10; i++) {
                String name = "Task_" + i;
                Task task = new Task(name);
                try {
                    /**
                     * 每次添加线程到线程池，都打印线程池的内部情况
                     */
                    threadPoolExecutor.execute(task);
                    System.out.println("PoolSize: " + threadPoolExecutor.getPoolSize() +
                            ",Queue" + threadPoolExecutor.getQueue());
                    System.out.println();
                    //Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Refused:" + name);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runTask);
        thread.start();
    }
}
