package Offer;


import java.util.Stack;

/**
 * 定义栈的数据结构，
 * 请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * <p>
 * 双栈实现：s1正常存数据,s2存放每次的最小值
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
