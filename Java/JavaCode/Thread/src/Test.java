import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试sleep是否放弃cpu：
 * 测试得到sleep放弃cpu
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
        // 使用lambda创建Runnable对象
        Runnable target = new Test();
        // 向线程池提交两个线程
//        for (int i = 0; i < 100; i++) {
//            pool.submit(target);
//        }
        //关闭线程池
        pool.submit(target);

        pool.shutdown();
    }
}
