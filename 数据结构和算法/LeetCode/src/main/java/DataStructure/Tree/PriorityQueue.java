package DataStructure.Tree;

import DataStructure.Queue.Queue;

import java.util.Comparator;

/*
通过 二叉堆 实现 优先队列：
实现Comparable接口，实现Queue<E>接口
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
    入队：通过二叉堆的add方法入队 O（n）
     */
    @Override
    public void enQueue(E element) {
        maxBinaryHeap.add(element);
    }
    /*
    出队:将最大元素，即优先级最大的节点出队
     */
    @Override
    public E deQueue() {
        return maxBinaryHeap.extractMax();
    }
}
