package Container;

import java.util.ArrayDeque;

/**
 * Queue--->PriorityQueueʵ����
 *    ح
 *    ح-->Deque˫�˶��У�������������������Ԫ��
 *            ح--->ArrayDequeʵ����ӿڣ���������ʵ�ֵ�˫�˶��У�����Ѿ�������Stack����ջ����Ҳ���Ե�������ʹ��
 * 
 * ��Ȼʱ���У�����PriorityQueue��Ѷ����е�Ԫ�ؽ��д�С��������
 */
class Queue_demo {

    public static void main(String[] args) {
        //��ArrayDeque��ջ��ʹ��,����Ѿ�������Stack
        System.out.println("����ջ��ʹ�ã��Ƚ������");
        ArrayDeque<String> stack_1 = new ArrayDeque<>();
        //��ջ--->push
        stack_1.push("abc1");
        stack_1.push("abc2");
        stack_1.push("abc3");
        System.out.println(stack_1);
        //���ʵ�һ��Ԫ�أ�����pop
        System.out.println(stack_1.peek());
        //pop��ջ
        System.out.println(stack_1.pop());
        System.out.println(stack_1+"\n");

        System.out.println("\r��������ʹ��,�Ƚ��ȳ���");
        ArrayDeque<String> queue_1 = new ArrayDeque<>();
        //������ʹ�ã�--->offer
        queue_1.offer("abc1");
        queue_1.offer("abc2");
        queue_1.offer("abc3");
        System.out.println(queue_1);
        //����ͷ��Ԫ�أ���poll��
        System.out.println(queue_1.peek());
        //poll����һ��Ԫ��
        System.out.println(queue_1.poll());
        System.out.println(queue_1);
    }
}