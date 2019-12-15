package DataStructure.Queue;

/*
ѭ�����е�ʵ��:(���ľ���ȡ����)
1.(tail+1)%data.length==front
    ��������д����%data.length����ʾ����������г��ȣ�ֱ���Զ���0��ʼ����
2.frontָ���һ��Ԫ��
  tailָ�����һ��Ԫ�صĺ���һ��λ�ã�tail��ָ��λ����ԶΪ��
3.��������еĳ��и��Ӷȴ�O��n��������O��1��
4.ѭ�����е����в�����ΪO��1��
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    //������ף���β
    private int front, tail;
    // ʵ��Ԫ�ظ���
    private int size;

    public LoopQueue(int capacity) {
        //�����˷�һ���ռ䣬front��tail�����ص�
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }
    // Ĭ�϶��д�СΪ10
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

    //���� 
    @Override
    public void enQueue(E element) {
        //�ж�tail����һ��λ�ã��ǲ���front������ǣ����ˣ�����
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = element;
        //����������г��ȣ�ֱ���Զ���0��ʼ����
        tail = (tail + 1) % data.length;
        size++;
    }
    // ����
    private void resize(int newCapacity) {
        //��ԭ�ж��У����·Ž�һ������,��0��ʼ��
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
            throw new IllegalArgumentException("����Ϊ��");
        E ret=data[front];
        data[front]=null;
        // front����
        front=(front+1)%data.length;
        size--;
        //����
        if (size==getCapacity()/4 && getCapacity()!=0)
            resize(getCapacity()/2);
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("����Ϊ��");
        return data[front];
    }
    //��дtoString,���Լ��ĸ�ʽ���
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
