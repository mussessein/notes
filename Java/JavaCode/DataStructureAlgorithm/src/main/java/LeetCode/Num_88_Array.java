package LeetCode;

/**
 * 给定两个有序整数数组 nums1 和 nums2
 * 将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 */

public class Num_88_Array {
    /**
     * 需要辅助空间的方法(参考归并排序)
     * 其实本题目在nums1中预留空间,就是希望不使用辅助空间来解答
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                arr[k++] = nums1[i++];
            } else
                arr[k++] = nums2[j++];
        }
        while (i < m) {
            arr[k++] = nums1[i++];
        }
        while (j < n) {
            arr[k++] = nums2[j++];
        }
        for (int l = 0; l < k; l++) {
            nums1[l] = arr[l];
        }
    }

    /**
     * 不使用辅助空间
     */
    public void merge_1(int[] nums1, int m, int[] nums2, int n) {

        int x = m + n - 1;
        m -= 1;
        n -= 1;
        while (m >= 0 && n >= 0) {
            nums1[x--] = nums1[m] >= nums2[n] ? nums1[m--] : nums2[n--];
        }
        /*
        只有一种剩余情况
        m用完了,n有剩余
         */
        while (n>=0){
            nums1[n]=nums2[n--];
        }


    }

}
