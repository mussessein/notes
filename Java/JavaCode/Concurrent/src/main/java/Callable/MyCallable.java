package Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable {

    @Override
    public Integer call() throws Exception {
        return 0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> ft = new FutureTask<>(new MyCallable());
        new Thread(ft).start();
        Integer res = ft.get();
        System.out.println(res);
    }
}
