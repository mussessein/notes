package Algorithm.Sort;

import java.util.Arrays;

/**
 * 快速排序算法
 * 关键是：找到基准位
 * 参考：https://blog.csdn.net/shujuelin/article/details/82423852
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            //先看右边，依次往左递减，找比temp小的值
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增，找比temp大的值
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        // 此时{x,x,x,x,x,tmp,y,y,y,y,y}:所有x小于tmp，所有y大于tmp，
        // 再排序所有x，再排序所有y
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }
    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}