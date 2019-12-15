package DataStructure.Queue;


import DataStructure.Array;

/*
数组队列的实现：
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){

        array=new Array<>(capacity);
    }
    public ArrayQueue(){

        array=new Array<>();
    }

    //列复杂度O（1）
    @Override
    public int getSize() {

        return array.getSize();
    }

    @Override
    public boolean isEmpty() {

        return array.isEmpty();
    }

    public int getCapacity(){

        return array.getCapacity();
    }

    //入队  列复杂度O（1）
    @Override
    public void enQueue(E element) {

        array.addLast(element);
    }
    // 出列:要维护索引
    // 复杂度O（n），使用循环队列的复杂度为O（1）
    @Override
    public E deQueue() {

        return array.removeFirst();
    }
    //查看第一个 ，列复杂度O（1）
    @Override
    public E getFront() {

        return array.getFirst();
    }
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Queue:");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {

        ArrayQueue a =new ArrayQueue(5);
        a.enQueue(5);
        a.enQueue(6);
        a.enQueue(8);
        a.enQueue(9);
        a.enQueue(1);
        a.enQueue(6);
        System.out.println(a);
        a.deQueue();
        System.out.println(a);
    }
}
