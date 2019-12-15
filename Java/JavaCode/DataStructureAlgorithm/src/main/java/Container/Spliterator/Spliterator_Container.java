package Container.Spliterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

/**
 * Spliterator��һ������������
 * ���Խ��������񣬷������ͬ���߳�
 * 1.�߳�������ҪSpliteratorʵ�����������յ�������
 * 2.��дrun�����У�ͨ��Spliterator.forEachRemaining��Consumer��
 */
public class Spliterator_Container {
    /**
     * �Զ����߳�
     */
    private static class MyThredIteratorThread<T> extends Thread {

        private final Spliterator<T> list;

        private MyThredIteratorThread(Spliterator<T> list) {
            this.list = list;
        }

        /**
         * �������������
         */
        @Override
        public void run() {
            /**
             * ʵ�����ǵ��õ�Consumer����ʽ�ӿ�
             * t:���ǵ���Ԫ��
             */
/*            list.forEachRemaining(new Consumer<T>() {
                @Override
                public void accept(T t) {
                    System.out.println(t);
                }
            });*/
            list.forEachRemaining(e -> System.out.println(e));
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(1000);
        for (int i = 0; i < 20000; i++) {
            list.add("bery" + i);
        }
        // ������䣺
        Spliterator<String> spliterator1 = list.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();
        Spliterator<String> spliterator3 = spliterator1.trySplit();
        Spliterator<String> spliterator4 = spliterator2.trySplit();

        MyThredIteratorThread<String> thread1 = new MyThredIteratorThread<>(spliterator1);
        MyThredIteratorThread<String> thread2 = new MyThredIteratorThread<>(spliterator2);
        MyThredIteratorThread<String> thread3 = new MyThredIteratorThread<>(spliterator3);
        MyThredIteratorThread<String> thread4 = new MyThredIteratorThread<>(spliterator4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
