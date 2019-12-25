package Offer;

import java.util.Arrays;

/**
 * 给定数组
 * 奇数排前面，偶数排后面，并且分别排序
 * 插入排序！！！
 */
public class solution_12 {
    public static void reOrderArray(int[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (!jugde(array[j])) {
                    if (array[j - 1] > array[j] || jugde(array[j - 1])) {
                        swap(array, j, j - 1);
                    }
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static boolean jugde(int n) {
        // 偶数true
        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
