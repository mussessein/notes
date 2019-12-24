package DataStructure;

/**
 * 泛型数组的实现：在add方法中增加动态扩容功能
 * ？？？？为什么remove方法，最后一个元素，自动消失
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

    //构造函数，传入数据的容量，并初始化长度为0
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //无参构造器，默认容量
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

    //获取数组中的元素个数
    public int size() {

        return size;
    }

    //获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    //返回数组是否为空
    public boolean isEmpty() {

        return size == 0;
    }

    //向指定位置index添加元素e，增加动态扩容功能[O(n/2)=O(n)]
    public void add(int index, E element) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        if (size == data.length)
            resize(2 * data.length);
        //插入位置后的元素，依次向后挪一位
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        //插入元素
        data[index] = element;
        size++;
    }

    //扩容 [O(n)]
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //向所有元素后添加一个元素[O(1)]
    public void addLast(E element) {

        add(size, element);
    }

    //在数组行首添加元素 [O(n)]
    public void addFirst(E element) {

        add(0, element);
    }

    //取出index索引元素
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

    //修改index的数值
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("AddLast failed,Array is full.");
        }
        data[index] = element;
    }

    //是否包含某个元素
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return true;
        }
        return false;
    }

    //查找指定元素的位置[O(n)]
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element))
                return i;
        }
        return -1;
    }

    //删除指定位置的元素,动态减少空间[O(n)]
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

    //删除第一个元素
    public E removeFirst() {

        return remove(0);
    }

    public E removeLast() {

        return remove(size - 1);
    }

    //删除数组内指定元素，先判断有无此元素
    public void removeElement(E element) {

        int index = find(element);
        //index 不等于-1 说明找到了此元素，且index为此元素的索引值
        if (index != -1) {
            remove(index);
        }
    }
    /*
    交换元素大小
     */
    public void swap(int i,int j){
        if (i<0||i>=size||j<0||j>=size)
            throw new IllegalArgumentException("index is illegal!");
        E t =data[i];
        data[i]=data[j];
        data[j]=t;
    }

    //重写toString,按自己的格式输出
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
    测试动态扩容和缩小
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
