package DataStructure.Tree;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        /*
        ����ѣ���Ӻ�ɾ�����Ԫ�ظ��Ӷȶ���O��log n��
        ���Զ����(������֣��Զ�����)
        */
        int n= 10000;
        Random random=new Random();
        Integer[] arr= new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i]=random.nextInt((1000));
        }
        Integer[] arr1= new Integer[n];
        Integer[] arr2= new Integer[n];

        /*
        add��������һ�������
         */
        long st1=System.currentTimeMillis();
        MaxBinaryHeap<Integer> maxBinaryHeap1=new MaxBinaryHeap<>();
        for (int i = 0; i < n; i++) {
            maxBinaryHeap1.add(arr[i]);
        }
        long end1=System.currentTimeMillis();
        for (int i = 0; i <n; i++) {
            arr1[i]=maxBinaryHeap1.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr1[i-1]<arr1[i])
                throw new IllegalArgumentException("Error1!");
        }
        System.out.println("Test1 completed!");

        /*
        Heapify ������������
         */
        long st2=System.currentTimeMillis();
        MaxBinaryHeap<Integer> maxBinaryHeap2=new MaxBinaryHeap<>(arr);
        long end2=System.currentTimeMillis();
        for (int i = 0; i <n; i++) {
            arr2[i]=maxBinaryHeap2.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if (arr2[i-1]<arr2[i])
                throw new IllegalArgumentException("Error1!");
        }
        System.out.println("Test1 completed!");

        /*
        �Ƚϲ���ʱ��:������ԣ�Heapify�ܿ�
         */
        System.out.println("add������ʱ��"+(end1-st1));
        System.out.println("Heapify������ʱ��"+(end2-st2));
    }

}
