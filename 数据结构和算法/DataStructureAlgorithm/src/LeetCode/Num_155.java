package LeetCode;

import java.util.Stack;

public class Num_155 {
     static class MinStack {
        private Stack<Integer> data;
        private Stack<Integer> min;

        public MinStack() {
            data = new Stack<Integer>();
            min = new Stack<Integer>();
        }

        public void push(int x) {
            data.push(x);
            int s =min.isEmpty() || x <= min.peek() ? min.push(x) : min.push(min.peek());
        }

        public void pop() {
            data.pop();
            min.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(-11);

    }
}
