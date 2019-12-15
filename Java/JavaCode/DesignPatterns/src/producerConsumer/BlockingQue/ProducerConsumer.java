package producerConsumer.BlockingQue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 需要的对象：
 * Storage仓库、Product产品、Producer生产者、Consumer消费者
 * 1、生产者仅仅在仓储未满时候生产，仓满则停止生产。
 * 2、消费者仅仅在仓储有产品时候才能消费，仓空则等待。
 * 3、当消费者发现仓储没产品可消费时候会通知生产者生产。
 * 4、生产者在生产出可消费产品时候，应该通知等待的消费者去消费。
 */
public class ProducerConsumer {
    public static void main(String[] args) {

        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        service.submit(p);
        service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);

    }

}
