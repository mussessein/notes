package CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {
    private String name;
    private CountDownLatch latch;

    public Task(String name) {
        this.name = name;
    }

    public Task(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(this.name + " Over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}