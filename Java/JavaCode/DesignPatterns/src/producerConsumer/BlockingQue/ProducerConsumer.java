package producerConsumer.BlockingQue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ��Ҫ�Ķ���
 * Storage�ֿ⡢Product��Ʒ��Producer�����ߡ�Consumer������
 * 1�������߽����ڲִ�δ��ʱ��������������ֹͣ������
 * 2�������߽����ڲִ��в�Ʒʱ��������ѣ��ֿ���ȴ���
 * 3���������߷��ֲִ�û��Ʒ������ʱ���֪ͨ������������
 * 4���������������������Ѳ�Ʒʱ��Ӧ��֪ͨ�ȴ���������ȥ���ѡ�
 */
public class ProducerConsumer {
    public static void main(String[] args) {

        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = new Producer("����", s);
        Producer p2 = new Producer("����", s);
        Consumer c = new Consumer("����", s);
        Consumer c2 = new Consumer("����", s);
        Consumer c3 = new Consumer("����", s);
        service.submit(p);
        service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);

    }

}
