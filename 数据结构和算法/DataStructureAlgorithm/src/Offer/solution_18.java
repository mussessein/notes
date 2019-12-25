package Offer;


import java.util.Stack;

/**
 * ����ջ�����ݽṹ��
 * ���ڸ�������ʵ��һ���ܹ��õ�ջ��������СԪ�ص�min������ʱ�临�Ӷ�ӦΪO��1������
 * <p>
 * ˫ջʵ�֣�s1����������,s2���ÿ�ε���Сֵ
 */
public class solution_18 {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int node) {
        if (s2.isEmpty() || node < s2.peek()) {
            s2.push(node);
        } else {
            s2.push(s2.peek());
        }
        s1.push(node);
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }
}
