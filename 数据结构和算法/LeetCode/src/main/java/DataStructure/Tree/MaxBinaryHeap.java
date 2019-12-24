package DataStructure.Tree;

import DataStructure.Array;

/*
堆：
添加和删除最大元素复杂度都是O（log n）
主流实现（二叉堆）： 通过二叉树实现堆
1.二叉堆是一个完全二叉树
2.完全二叉树：元素按层依次放入，每一层都是从左至右依次添加
            加上索引之后：任意
3.最大堆：堆中的每个节点的值，总是不大于其父节点（根节点是整个树最大的节点）
 */
/*
数组实现的最大堆
 */
public class MaxBinaryHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxBinaryHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxBinaryHeap() {
        data = new Array<>();
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }
    /*
    通过传入index，下标，找到父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("No parent!");
        return (index - 1) / 2;
    }

    /*
    找到此节点下的leftChild的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /*
    找到此节点下的rightChild的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }
    /*
    Heapify:(传入数组，构造二叉堆)
    1.堆化（将任意数组排列成二叉堆的形式） 复杂度O（n）
    2.过程：从倒数第二层开始，每个节点不断下沉SiftDown
    如果单纯将数据从头到尾构造成二叉堆的话：复杂度为O（nlogn）
    */
    public MaxBinaryHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    /*
    向堆中添加元素
    sift up（上浮）
    1.要添加的元素，从树的最右下开始添加
    2.然后，将添加的元素，不断地与父亲节点进行比较
    3.如果新添加的节点比较大，与父亲节点交换元素值，
    4.然后继续和父亲节点比较，直到小于父亲节点
     */
    public void add(E element) {
        //添加到最尾端
        data.addLast(element);
        //开始上浮
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        // index元素和父亲元素进行比较
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 父亲节点小，则交换值
            data.swap(k, parent(k));
            // 将index设为换过位置的父类，继续与上层比较
            k = parent(k);
        }
    }

    /*
    取出元素（下沉Sift Down）
    1.假如要把root取出
    2.直接将最后一个元素删除，并赋值给root，
    2.然后root元素，不断与子节点交换值
    3.比较左右子节点，与较大的子节点，交换值，直到结束
     */
    //返回最大元素的值
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("heap is empty!");
        return data.get(0);
    }

    //取出最大元素（取出并删除）
    public E extractMax() {
        E ret = findMax();
        //将最大元素和最小元素，交换值,删除最后一个元素即交换后的最大值
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 将第一个元素开始下沉
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        /*
         1.先判断，左孩子的索引小于最大值，即左孩子存在（之判断左，是因为完全二叉树从左开始）
         2.无论左右谁大，将最大的节点的索引给 j ，最后只需要交换k，j 的值
         3.如果k大于等于j，跳出循环，结束
         4.交换k，j，进行下一轮
          */
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    /*
    replace替换操作：（取出最大元素，替换为元素e）
    直接将e赋值给root，再SiftDown
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
