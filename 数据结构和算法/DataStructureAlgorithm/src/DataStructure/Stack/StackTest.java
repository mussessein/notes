package DataStructure.Stack;
/*
比较两种栈的性能差异：
    链表在栈的优势在于数量少的情况。当操作数增大，链表栈需要很多new操作，会变慢

 */
public class StackTest {

    public static void main(String[] args) {
        /*
        数组实现的栈
         */
        ArrayStack<Integer> stack1 = new ArrayStack<>();
        long st1 =System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            stack1.push(i);
        }
        for (int i = 0; i < 1000000; i++) {
            stack1.pop();
        }
        long end1 =System.currentTimeMillis();
        /*
        链表实现的栈
         */
        LinkedListStack<Integer> stack2 = new LinkedListStack<>();
        long st2 =System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            stack2.push(i);
        }
        for (int i = 0; i < 1000000; i++) {
            stack2.pop();
        }
        long end2 =System.currentTimeMillis();
        //毫秒差
        System.out.println("数组栈："+(end1-st1));
        System.out.println("链表栈："+(end2-st2));
    }
}
