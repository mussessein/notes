package producerConsumer.BlockingQue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Storage {
    BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10);

    /**
     * 生产
     */
    public void push(Product p) throws InterruptedException {
        // 队尾放入元素，已满阻塞
        queues.put(p);
    }

    /**
     * 消费
     */
    public Product pop() throws InterruptedException {
        // 队列首 删除元素，已空阻塞
        return queues.take();
    }
}
