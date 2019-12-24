package DataStructure.Stack;

public interface Stack<E> {

    // 容量
    int getSize();
    // 是否为空
    boolean isEmpty();
    // 入栈
    void push(E element);
    // 出栈
    E pop();
    // 返回栈顶元素,但不出栈
    E peek();
}
