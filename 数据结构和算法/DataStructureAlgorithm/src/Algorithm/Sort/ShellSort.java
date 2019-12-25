package Algorithm.Sort;

import java.util.Arrays;

public class ShellSort {
    public static void sort(int[] arr) {
        int len = arr.length;
        int h = 1;
        while (h < len / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (arr[j] < arr[j - h]) {
                        int tmp = arr[j - h];
                        arr[j - h] = arr[j];
                        arr[j] = tmp;
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {

        int[] arr = {8, 3, 1, 4, 5, 9, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
