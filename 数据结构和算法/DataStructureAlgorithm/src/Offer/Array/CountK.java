package Offer.Array;

/**
 * ͳ��һ�����������������г��ֵĴ�����
 */
public class CountK {
    public static int GetNumberOfK(int[] array, int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == k) {
                count++;
            } else if (count != 0) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 5, 6, 7, 8};
        System.out.println(GetNumberOfK(arr, 5));
    }
}
