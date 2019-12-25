package Algorithm.Sort;

import java.util.Arrays;

public class InsertSort {
    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 3, 1, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
