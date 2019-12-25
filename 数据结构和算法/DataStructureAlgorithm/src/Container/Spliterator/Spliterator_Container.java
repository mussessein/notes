package Container.Spliterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

/**
 * Spliterator是一个并发迭代器
 * 可以将迭代任务，分配给不同的线程
 * 1.线程类中需要Spliterator实例对象，来接收迭代任务
 * 2.重写run方法中，通过Spliterator.forEachRemaining（Consumer）
 */
public class Spliterator_Container {
    /**
     * 自定义线程
     */
    private static class MyThredIteratorThread<T> extends Thread {

        private final Spliterator<T> list;

        private MyThredIteratorThread(Spliterator<T> list) {
            this.list = list;
        }

        /**
         * 定义迭代器任务
         */
        @Override
        public void run() {
            /**
             * 实际上是调用的Consumer函数式接口
             * t:就是迭代元素
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
        // 任务分配：
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
