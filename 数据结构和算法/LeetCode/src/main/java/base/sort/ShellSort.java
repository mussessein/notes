package base.sort;

import java.util.Arrays;

/**
 * @author whr
 * @date 2019/12/23 10:23
 * 希尔排序
 * 1. 插入排序的增强版，插入排序间隔始终为 1
 * 2. 希尔排序间隔 h，不断减少
 * 时间复杂度：O(n3/2)
 */
public class ShellSort {
    public static void sort(int[] arr) {
        int len = arr.length;
        int h = 1;
        while (h < (len / 3)) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (arr[j] < arr[j - h]) {
                        int tmp = arr[j];
                        arr[j] = arr[j - h];
                        arr[j - h] = tmp;
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 4, 5, 7, 2, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
