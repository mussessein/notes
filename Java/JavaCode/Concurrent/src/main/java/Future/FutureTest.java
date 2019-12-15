package Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        /**
         * Future实现
         */
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
            int res = result.get(); // 阻塞方法
            System.out.println("任务结果：" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Task 结束");
        /**
         * FutureTask 实现
         */
/*        FutureTask futureTask = new FutureTask(task);
        executorService.submit(futureTask);
        executorService.shutdown();
        try{
            Thread.sleep(1000);
            // 返回Object对象
            Integer res =(Integer)futureTask.get();
            System.out.println("任务结果："+res);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Task 结束");*/

    }

}
