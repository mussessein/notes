package Offer;

import java.util.Stack;

/**
 * ����վʵ��һ������:
 * ������ӵ�stcak1
 * ���Ǵ�stack2ȡ
 */
public class Solution_5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.empty()) {
            while (!stack1.empty()) {
                int cur = stack1.pop();
                stack2.push(cur);
            }
        }
        return stack2.pop();
    }
}
