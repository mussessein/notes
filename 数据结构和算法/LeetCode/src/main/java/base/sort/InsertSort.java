package base.sort;

import java.util.Arrays;

/**
 * @author whr
 * 插入排序
 * 时间复杂度：O(n2)
 */
public class InsertSort {

    public static void sort(int[] arr) {
        int len = arr.length;
        if (len <= 1) {
            return;
        }
        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 4, 5, 7, 2, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
