package Algorithm.Sort;

import java.util.Arrays;

public class test {
    public static void quickSort(int[] arr) {
        int low = 0, high = arr.length - 1;
        quickSort(arr, low, high);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int tmp = arr[low];
        int i = low, j = high;
        while (i < j) {
            while (arr[j] >= tmp && j > i)
                j--;
            while (arr[i] <= tmp && j > i)
                i++;
            if (i < j) {
                int t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        arr[low] = arr[i];
        arr[i] = tmp;
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
