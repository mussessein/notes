package DataStructure.Tree;

import DataStructure.Queue.Queue;

import java.util.Comparator;

/*
ͨ�� ����� ʵ�� ���ȶ��У�
ʵ��Comparable�ӿڣ�ʵ��Queue<E>�ӿ�
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxBinaryHeap<E> maxBinaryHeap;

    public PriorityQueue(Comparator<Integer> comparator){
        maxBinaryHeap =new MaxBinaryHeap<>();
    }

    @Override
    public int getSize() {
        return maxBinaryHeap.size();
    }
    @Override
    public boolean isEmpty(){
        return maxBinaryHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxBinaryHeap.findMax();
    }
    /*
    ��ӣ�ͨ������ѵ�add������� O��n��
     */
    @Override
    public void enQueue(E element) {
        maxBinaryHeap.add(element);
    }
    /*
    ����:�����Ԫ�أ������ȼ����Ľڵ����
     */
    @Override
    public E deQueue() {
        return maxBinaryHeap.extractMax();
    }
}
