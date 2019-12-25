package LeetCode;

import java.util.Stack;

/*

栈 的典型题目

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
输入: "([)]"
输出: false
输入: "{[]}"
输出: true
 */
public class Num_20Stack {

        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                if (stack.size() == 0) {
                    stack.push(aChar);
                } else if (isSym(stack.peek(), aChar)) {
                    stack.pop();
                } else {
                    stack.push(aChar);
                }
            }
            return stack.size() == 0;
        }

        private boolean isSym(char c1, char c2) {
            return (c1 == '(' && c2 == ')') || (c1 == '[' && c2 == ']') || (c1 == '{' && c2 == '}');
        }

}
