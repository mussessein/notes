package DataStructure.Stack;

public interface Stack<E> {

    // ����
    int getSize();
    // �Ƿ�Ϊ��
    boolean isEmpty();
    // ��ջ
    void push(E element);
    // ��ջ
    E pop();
    // ����ջ��Ԫ��,������ջ
    E peek();
}
