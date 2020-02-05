package base.sort;

import java.util.Arrays;

/**
 * @author whr
 * @date 2019/12/23 10:32
 * 归并排序
 * 时间复杂度：O(nlogn),空间复杂度O(n)
 * 1. 分段：不断二分
 * 2. 排序，需要额外空间，存放中间结果
 */
public class MergeSort {
    /**
     * 入口
     */
    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        segment(arr, low, high);
    }

    /**
     * 分段
     */
    private static void segment(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            segment(arr, low, mid);
            segment(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    /**
     * 排序
     */
    private static void merge(int[] arr, int low, int mid, int high) {
        int[] tmp = new int[arr.length];
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= high) {
            tmp[k++] = arr[j++];
        }
        for (int l = low; l <= high; l++) {
            arr[l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 4, 5, 7, 2, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
