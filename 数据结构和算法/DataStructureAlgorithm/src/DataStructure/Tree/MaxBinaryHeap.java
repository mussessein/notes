package DataStructure.Tree;

import DataStructure.Array;

/*
�ѣ�
��Ӻ�ɾ�����Ԫ�ظ��Ӷȶ���O��log n��
����ʵ�֣�����ѣ��� ͨ��������ʵ�ֶ�
1.�������һ����ȫ������
2.��ȫ��������Ԫ�ذ������η��룬ÿһ�㶼�Ǵ��������������
            ��������֮������
3.���ѣ����е�ÿ���ڵ��ֵ�����ǲ������丸�ڵ㣨���ڵ������������Ľڵ㣩
 */
/*
����ʵ�ֵ�����
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
    ͨ������index���±꣬�ҵ����׽ڵ������
     */
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("No parent!");
        return (index - 1) / 2;
    }

    /*
    �ҵ��˽ڵ��µ�leftChild������
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /*
    �ҵ��˽ڵ��µ�rightChild������
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }
    /*
    Heapify:(�������飬��������)
    1.�ѻ����������������гɶ���ѵ���ʽ�� ���Ӷ�O��n��
    2.���̣��ӵ����ڶ��㿪ʼ��ÿ���ڵ㲻���³�SiftDown
    ������������ݴ�ͷ��β����ɶ���ѵĻ������Ӷ�ΪO��nlogn��
    */
    public MaxBinaryHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }


    /*
    ��������Ԫ��
    sift up���ϸ���
    1.Ҫ��ӵ�Ԫ�أ������������¿�ʼ���
    2.Ȼ�󣬽���ӵ�Ԫ�أ����ϵ��븸�׽ڵ���бȽ�
    3.�������ӵĽڵ�Ƚϴ��븸�׽ڵ㽻��Ԫ��ֵ��
    4.Ȼ������͸��׽ڵ�Ƚϣ�ֱ��С�ڸ��׽ڵ�
     */
    public void add(E element) {
        //��ӵ���β��
        data.addLast(element);
        //��ʼ�ϸ�
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        // indexԪ�غ͸���Ԫ�ؽ��бȽ�
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // ���׽ڵ�С���򽻻�ֵ
            data.swap(k, parent(k));
            // ��index��Ϊ����λ�õĸ��࣬�������ϲ�Ƚ�
            k = parent(k);
        }
    }

    /*
    ȡ��Ԫ�أ��³�Sift Down��
    1.����Ҫ��rootȡ��
    2.ֱ�ӽ����һ��Ԫ��ɾ��������ֵ��root��
    2.Ȼ��rootԪ�أ��������ӽڵ㽻��ֵ
    3.�Ƚ������ӽڵ㣬��ϴ���ӽڵ㣬����ֵ��ֱ������
     */
    //�������Ԫ�ص�ֵ
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("heap is empty!");
        return data.get(0);
    }

    //ȡ�����Ԫ�أ�ȡ����ɾ����
    public E extractMax() {
        E ret = findMax();
        //�����Ԫ�غ���СԪ�أ�����ֵ,ɾ�����һ��Ԫ�ؼ�����������ֵ
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // ����һ��Ԫ�ؿ�ʼ�³�
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        /*
         1.���жϣ����ӵ�����С�����ֵ�������Ӵ��ڣ�֮�ж�������Ϊ��ȫ����������ʼ��
         2.��������˭�󣬽����Ľڵ�������� j �����ֻ��Ҫ����k��j ��ֵ
         3.���k���ڵ���j������ѭ��������
         4.����k��j��������һ��
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
    replace�滻��������ȡ�����Ԫ�أ��滻ΪԪ��e��
    ֱ�ӽ�e��ֵ��root����SiftDown
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
