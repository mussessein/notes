package DataStructure.Queue;

public interface Queue<E> {
    /**
     * ��ջ��������
     */
    int getSize();
    boolean isEmpty();
    void enQueue(E element);
    E deQueue();
    E getFront();
}
