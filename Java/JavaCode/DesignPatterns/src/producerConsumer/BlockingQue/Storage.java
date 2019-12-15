package producerConsumer.BlockingQue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage {
    BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);

    /**
     * ����
     */
    public void push(Product p) throws InterruptedException {
        // ��β����Ԫ�أ���������
        queues.put(p);
    }

    /**
     * ����
     */
    public Product pop() throws InterruptedException {
        // ������ ɾ��Ԫ�أ��ѿ�����
        return queues.take();
    }
}
