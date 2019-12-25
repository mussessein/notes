package DataStructure.Tree;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        /*
        二叉堆：添加和删除最大元素复杂度都是O（log n）
        测试二叉堆(添加数字，自动排序)
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
        add方法添构造一个二叉堆
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
        Heapify 方法构造二叉堆
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
        比较操作时间:差距明显，Heapify很快
         */
        System.out.println("add方法用时："+(end1-st1));
        System.out.println("Heapify方法用时："+(end2-st2));
    }

}
