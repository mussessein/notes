package DataStructure;

/**
 * ���������ʵ�֣���add���������Ӷ�̬���ݹ���
 * ��������Ϊʲôremove���������һ��Ԫ�أ��Զ���ʧ
 */
public class Array<E> {
    private E[] data;
    private int size;

    public E[] getData() {
        return data;
    }

    public void setData(E[] data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    //���캯�����������ݵ�����������ʼ������Ϊ0
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //�޲ι�������Ĭ������
    public Array() {

        this(10);
    }
    public Array(E[] arr){
        data=(E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i]=arr[i];
        }
        size=arr.length;
    }

    //��ȡ�����е�Ԫ�ظ���
    public int size() {

        return size;
    }

    //��ȡ���������
    public int getCapacity() {
        return data.length;
    }

    //���������Ƿ�Ϊ��
    public boolean isEmpty() {

        return size == 0;
    }

    //��ָ��λ��index���Ԫ��e�����Ӷ�̬���ݹ���[O(n/2)=O(n)]
    public void add(int index, E element) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        if (size == data.length)
            resize(2 * data.length);
        //����λ�ú��Ԫ�أ��������Ųһλ
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //����Ԫ��
        data[index] = element;
        size++;
    }

    //���� [O(n)]
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //������Ԫ�غ����һ��Ԫ��[O(1)]
    public void addLast(E element) {

        add(size, element);
    }

    //�������������Ԫ�� [O(n)]
    public void addFirst(E element) {

        add(0, element);
    }

    //ȡ��index����Ԫ��
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        }
        return data[index];
    }
    public E getLast(){

        return get(size-1);
    }
    public E getFirst(){

        return get(0);
    }

    //�޸�index����ֵ
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        }
        data[index] = element;
    }

    //�Ƿ����ĳ��Ԫ��
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return true;
        }
        return false;
    }

    //����ָ��Ԫ�ص�λ��[O(n)]
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return i;
        }
        return -1;
    }

    //ɾ��ָ��λ�õ�Ԫ��,��̬���ٿռ�[O(n)]
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        if (size == data.length / 2 && data.length/2!=0)
            resize(data.length / 2);
        size--;
        return data[index];
    }

    //ɾ����һ��Ԫ��
    public E removeFirst() {

        return remove(0);
    }

    public E removeLast() {

        return remove(size - 1);
    }

    //ɾ��������ָ��Ԫ�أ����ж����޴�Ԫ��
    public void removeElement(E element) {

        int index = find(element);
        //index ������-1 ˵���ҵ��˴�Ԫ�أ���indexΪ��Ԫ�ص�����ֵ
        if (index != -1) {
            remove(index);
        }
    }
    /*
    ����Ԫ�ش�С
     */
    public void swap(int i,int j){
        if (i<0||i>=size||j<0||j>=size)
            throw new IllegalArgumentException("index is illegal!");
        E t =data[i];
        data[i]=data[j];
        data[j]=t;
    }

    //��дtoString,���Լ��ĸ�ʽ���
    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(',');
        }
        res.append(']');
        return res.toString();
    }

    /*
    ���Զ�̬���ݺ���С
     */
    public static void main(String[] args) {
        Array<Integer> arr1 = new Array<Integer>();
        for (int i = 0; i < 10; i++) {
            arr1.addLast(i);
        }
        System.out.println(arr1);
        arr1.addLast(1);
        System.out.println(arr1);
        Array<Integer> arr2 = new Array<Integer>();
        for (int i = 0; i < 5; i++) {
            arr2.addLast(i);
        }
        System.out.println(arr2);
        arr2.remove(4);
        System.out.println(arr2);

    }
}
