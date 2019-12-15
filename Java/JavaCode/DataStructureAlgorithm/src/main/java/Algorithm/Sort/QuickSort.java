package Algorithm.Sort;

import java.util.Arrays;

/**
 * ���������㷨
 * �ؼ��ǣ��ҵ���׼λ
 * �ο���https://blog.csdn.net/shujuelin/article/details/82423852
 */
public class QuickSort {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp���ǻ�׼λ
        temp = arr[low];
        while (i < j) {
            //�ȿ��ұߣ���������ݼ����ұ�tempС��ֵ
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //�ٿ���ߣ��������ҵ������ұ�temp���ֵ
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //������������򽻻�
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //��󽫻�׼Ϊ��i��j���λ�õ����ֽ���
        arr[low] = arr[i];
        arr[i] = temp;
        // ��ʱ{x,x,x,x,x,tmp,y,y,y,y,y}:����xС��tmp������y����tmp��
        // ����������x������������y
        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);
    }
    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}