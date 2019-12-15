package ThreadPool.ThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ��дRejectedExecutionHandler
 * �Զ����̳߳ؾܾ��߳�֮�����Ϊ
 */
public class MyRejectedHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Rejected��" + r.toString());
    }
}
