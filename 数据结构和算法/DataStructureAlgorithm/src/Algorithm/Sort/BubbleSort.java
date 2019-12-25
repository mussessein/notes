package Algorithm.Sort;

import java.util.Arrays;

public class    BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 3, 1, 4, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
