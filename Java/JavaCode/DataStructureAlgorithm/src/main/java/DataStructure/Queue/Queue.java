package DataStructure.Queue;

public interface Queue<E> {
    /**
     * 如栈基本类似
     */
    int getSize();
    boolean isEmpty();
    void enQueue(E element);
    E deQueue();
    E getFront();
}
