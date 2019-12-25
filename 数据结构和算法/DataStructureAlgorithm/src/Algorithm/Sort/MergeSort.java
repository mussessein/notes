package Algorithm.Sort;

import java.util.Arrays;

public class MergeSort {
    public static void merge(int[] arr) {
        int low = 0, high = arr.length - 1;
        merge(arr, low, high);
    }

    private static void merge(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            merge(arr, low, mid);
            merge(arr, mid + 1, high);
            sort(arr, low, mid, high);
        }
    }

    private static void sort(int[] arr, int low, int mid, int high) {
        int[] tmp = new int[arr.length];
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]){
                tmp[k++] = arr[i++];
            }
            else{
                tmp[k++] = arr[j++];
            }
        }
        // 排序剩余数组
        while (i <= mid) {
            tmp[k++] = arr[i++];
        }
        while (j <= high) {
            tmp[k++] = arr[j++];
        }
        // 将tmp赋值给arr
        for (int l = low; l <= high; l++) {
            arr[l] = tmp[l];
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 3, 1, 4, 5, 9, 2};
        merge(arr);
        System.out.println(Arrays.toString(arr));
    }
}
