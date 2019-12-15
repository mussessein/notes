package ThreadPool.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
ͨ���̳߳���ִ���̣߳�
ͨ��������Է��֣��̵߳�����ǰ����pool��˵����ʹ���̳߳���ִ��Runnable�����
 */
public class ExecutorsTest {

    public static void main(String[] args) {
        // ����һ�����й̶��߳���6���̳߳�
        ExecutorService pool = Executors.newFixedThreadPool(6);
        // ʹ��lambda����Runnable����
        Runnable target = () -> {
            for (int i = 0; i < 20; i++) {
                System.out.println(Thread.currentThread().getName() + "---iֵ��" + i);
            }
        };
        // ���̳߳��ύ�����߳�
        pool.submit(target);
        pool.submit(target);
        //�ر��̳߳�
        pool.shutdown();
    }
}
