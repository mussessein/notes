package base.sort;


import java.util.Arrays;

/**
 * @author whr
 * 冒泡排序
 * 时间复杂度：O(n2)
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 4, 5, 7, 2, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
