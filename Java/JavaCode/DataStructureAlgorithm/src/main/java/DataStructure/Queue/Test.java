package DataStructure.Queue;
/*
�Ա�1000����Ӻͳ��ӣ�LoopQueue��ArrayQueueҪ��ö�
 */
public class Test {
    public static void main(String[] args) {
        /*
        �������O(n)
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
        System.out.println("ArrayQueueִ��ʱ��"+(endtime2-starttime2));
        /*
         ѭ������O(1)
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
        System.out.println("LoopQueueִ��ʱ��"+(endtime1-starttime1));
        /*
        �������O(1)
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
        System.out.println("LinkedListQueueִ��ʱ��"+(endtime1-starttime1));


    }
}
