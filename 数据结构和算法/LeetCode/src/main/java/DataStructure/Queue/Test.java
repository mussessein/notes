package DataStructure.Queue;
/*
对比1000此入队和出队，LoopQueue比ArrayQueue要快得多
 */
public class Test {
    public static void main(String[] args) {
        /*
        数组队列O(n)
         */
        long starttime2=System.currentTimeMillis();
        ArrayQueue<Integer> queue2 =new ArrayQueue<>();
        for (int i = 0; i < 1000; i++) {
            queue2.enQueue(i);
        }

        for (int i = 0; i < 1000; i++) {
            queue2.deQueue();
        }
        long endtime2=System.currentTimeMillis();
        System.out.println("ArrayQueue执行时间"+(endtime2-starttime2));
        /*
         循环队列O(1)
         */
        long starttime1=System.currentTimeMillis();
        LoopQueue<Integer> queue1 =new LoopQueue<>();
        for (int i = 0; i < 1000; i++) {
            queue1.enQueue(i);
        }
        for (int i = 0; i < 1000; i++) {
            queue1.deQueue();
        }
        long endtime1=System.currentTimeMillis();
        System.out.println("LoopQueue执行时间"+(endtime1-starttime1));
        /*
        链表队列O(1)
         */
        long starttime3=System.currentTimeMillis();
        LinkedListQueue<Integer> queue3 =new LinkedListQueue<>();
        for (int i = 0; i < 1000; i++) {
            queue3.enQueue(i);
        }
        for (int i = 0; i < 1000; i++) {
            queue3.deQueue();
        }
        long endtime3=System.currentTimeMillis();
        System.out.println("LinkedListQueue执行时间"+(endtime1-starttime1));


    }
}
