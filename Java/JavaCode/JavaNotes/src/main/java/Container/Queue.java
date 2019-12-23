package Container;

import java.util.ArrayDeque;

/**
 * Queue--->PriorityQueue实现类
 *    丨
 *    丨-->Deque双端队列，允许两端来操作队列元素
 *            丨--->ArrayDeque实现类接口，基于数组实现的双端队列，如今已经代替了Stack（堆栈），也可以当队列来使用
 * 
 * 虽然时队列，但是PriorityQueue会把队列中的元素进行大小重新排序，
 */
class Queue_demo {

    public static void main(String[] args) {
        //将ArrayDeque当栈来使用,如今已经代替了Stack
        System.out.println("当堆栈来使用，先进后出：");
        ArrayDeque<String> stack_1 = new ArrayDeque<>();
        //堆栈--->push
        stack_1.push("abc1");
        stack_1.push("abc2");
        stack_1.push("abc3");
        System.out.println(stack_1);
        //访问第一个元素，并不pop
        System.out.println(stack_1.peek());
        //pop出栈
        System.out.println(stack_1.pop());
        System.out.println(stack_1+"\n");

        System.out.println("\r当队列来使用,先进先出：");
        ArrayDeque<String> queue_1 = new ArrayDeque<>();
        //当队列使用：--->offer
        queue_1.offer("abc1");
        queue_1.offer("abc2");
        queue_1.offer("abc3");
        System.out.println(queue_1);
        //访问头部元素，不poll出
        System.out.println(queue_1.peek());
        //poll出第一个元素
        System.out.println(queue_1.poll());
        System.out.println(queue_1);
    }
}