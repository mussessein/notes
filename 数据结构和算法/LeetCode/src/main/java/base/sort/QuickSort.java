package base.sort;

import java.util.Arrays;

/**
 * @author whr
 * 快速排序
 * 1. 找基准位
 * 2. 递归
 * 参考：https://blog.csdn.net/shujuelin/article/details/82423852
 */
public class QuickSort {

    public static void sort(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        quickSort(arr,low,high);
    }
    private static void quickSort(int[] arr,int low,int high){
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        // 基准位
        int base = arr[low];
        while (i < j) {
            while (arr[j] >= base && j>i){
                j--;
            }
            while (arr[i] <= base && j > i) {
                i++;
            }
            if (i < j) {
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }
        // i,j重合
        arr[low]=arr[i];
        arr[i]=base;
        // base再参与排序
        quickSort(arr,low,i-1);
        quickSort(arr,i+1,high);
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 4, 5,7,2,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
