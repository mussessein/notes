package DataStructure.Queue;

/*
循环队列的实现:(核心就是取余数)
1.(tail+1)%data.length==front
    类似这种写法，%data.length，表示如果超出队列长度，直接自动从0开始计数
2.front指向第一个元素
  tail指向最后一个元素的后面一个位置，tail的指向位置永远为空
3.将数组队列的出列复杂度从O（n）降到了O（1）
4.循环队列的所有操作都为O（1）
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    //定义队首，队尾
    private int front, tail;
    // 实际元素个数
    private int size;

    public LoopQueue(int capacity) {
        //刻意浪费一个空间，front和tail不会重叠
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }
    // 默认队列大小为10
    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {

        return data.length - 1;
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public boolean isEmpty() {

        return front == tail;
    }

    //入列 
    @Override
    public void enQueue(E element) {
        //判断tail的下一个位置，是不是front，如果是，满了，扩容
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = element;
        //如果超出队列长度，直接自动从0开始计数
        tail = (tail + 1) % data.length;
        size++;
    }
    // 扩容
    private void resize(int newCapacity) {
        //将原有队列，重新放进一个队列,从0开始放
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E deQueue() {

        if (isEmpty())
            throw new IllegalArgumentException("队列为空");
        E ret=data[front];
        data[front]=null;
        // front后移
        front=(front+1)%data.length;
        size--;
        //缩容
        if (size==getCapacity()/4 && getCapacity()!=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("队列为空");
        return data[front];
    }
    //重写toString,按自己的格式输出
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue:size=%d,capacity=%d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i < tail; i=(i+1)%data.length) {
            res.append(data[i]);
            if ((i+1)%data.length!=tail)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(0%10);
    }
}
