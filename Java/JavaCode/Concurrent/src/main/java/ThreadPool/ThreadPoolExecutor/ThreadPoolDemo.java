package ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * ThreadPoolExecutor��ʹ��ʵ����
 * �ĸ���Ҫ����,
 * RejectedHandlerΪ�Լ������RejectedExecutionHandlerʵ����
 * �߳����̣�
 * 1.������ĳأ����ĳ����ˣ�֮���̣߳��������
 * 2.�������ˣ����������̣߳�ֱ������߳���
 * 3.����߳����������ܾ������߳�
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        /**
         * �����̳߳ز�����
         * �����̣߳�2
         * ����̣߳�3
         * �������д�С��5
         * �ܾ����ԣ��Զ���
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 3, 2000,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue(5), new MyRejectedHandler());

        // ����һ�����̣߳��ڲ�����10�����߳���ӽ��̳߳�
        Runnable runTask = () -> {
            for (int i = 0; i < 10; i++) {
                String name = "Task_" + i;
                Task task = new Task(name);
                try {
                    /**
                     * ÿ������̵߳��̳߳أ�����ӡ�̳߳ص��ڲ����
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
