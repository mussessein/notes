package DataStructure.Stack;
/*
�Ƚ�����ջ�����ܲ��죺
    ������ջ���������������ٵ����������������������ջ��Ҫ�ܶ�new�����������

 */
public class StackTest {

    public static void main(String[] args) {
        /*
        ����ʵ�ֵ�ջ
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
        ����ʵ�ֵ�ջ
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
        //�����
        System.out.println("����ջ��"+(end1-st1));
        System.out.println("����ջ��"+(end2-st2));
    }
}
