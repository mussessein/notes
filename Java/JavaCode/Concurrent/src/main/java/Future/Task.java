package Future;

import java.util.concurrent.Callable;

public class Task implements Callable {

    @Override
    public Integer call() throws InterruptedException {
        System.out.println("Task Starting");
        Thread.sleep(1000);
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        return sum;
    }
}
