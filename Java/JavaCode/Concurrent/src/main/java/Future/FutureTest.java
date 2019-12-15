package Future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        /**
         * Futureʵ��
         */
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
            int res = result.get(); // ��������
            System.out.println("��������" + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Task ����");
        /**
         * FutureTask ʵ��
         */
/*        FutureTask futureTask = new FutureTask(task);
        executorService.submit(futureTask);
        executorService.shutdown();
        try{
            Thread.sleep(1000);
            // ����Object����
            Integer res =(Integer)futureTask.get();
            System.out.println("��������"+res);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Task ����");*/

    }

}
