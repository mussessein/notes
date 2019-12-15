import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long starttime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(10000);

        for (int i = 0; i < 10000; i++) {
            pool.submit(new Test2());
        }
        //¹Ø±ÕÏß³Ì³Ø
        pool.shutdown();
        long endtime = System.currentTimeMillis();
        Thread.sleep(100);
        System.out.println(endtime - starttime);
    }
}
