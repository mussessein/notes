package MyThread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public String call() throws Exception {
        return "实现Callable接口";
    }

    public static void main(String[] args) {
        // 继承Thread——对Thread类的抽象
        MyThread t1 = new MyThread();
        t1.start();
        Thread t1_1 = new Thread(t1, "t_1_1");
        Thread t1_2 = new Thread(t1, "t_1_2");
        Thread t1_3 = new Thread(t1, "t_1_3");
        t1_1.start();
        // 实现Runnable接口——对任务的抽象
        MyRunnable myRunnable = new MyRunnable();
        Thread t2 = new Thread(myRunnable, "t2");
        t2.start();
        // 实现Callable接口——有返回值的任务
        MyCallable t3 = new MyCallable();
        try {
            String res = t3.call();
            System.out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
