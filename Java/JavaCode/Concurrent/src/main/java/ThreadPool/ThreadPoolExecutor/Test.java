package ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2,
                        3,
                        2000, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue(3),
                        new MyRejectedHandler());

        String[] tasks = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < 7; i++) {
            Task task = new Task(tasks[i]);
            threadPoolExecutor.execute(task);
        }
//        Task task1 =new Task("a");
//        Task task2 =new Task("b");
//        Task task3 =new Task("c");
//        Task task4 =new Task("d");
//        Task task5 =new Task("e");
//        Task task6 =new Task("f");
//        Task task7 =new Task("g");
//
//        threadPoolExecutor.execute(task1);
//        threadPoolExecutor.execute(task2);
//        threadPoolExecutor.execute(task3);
//        threadPoolExecutor.execute(task4);
//        threadPoolExecutor.execute(task5);
//        threadPoolExecutor.execute(task6);
//        threadPoolExecutor.execute(task7);

        threadPoolExecutor.shutdown();

    }
}
