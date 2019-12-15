package LeetCode;

/**
 * �������������������� nums1 �� nums2
 * �� nums2 �ϲ��� nums1 �У�ʹ�� num1 ��Ϊһ����������
 * <p>
 * ����:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * ���: [1,2,2,3,5,6]
 */

public class Num_88_Array {
    /**
     * ��Ҫ�����ռ�ķ���(�ο��鲢����)
     * ��ʵ����Ŀ��nums1��Ԥ���ռ�,����ϣ����ʹ�ø����ռ������
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
     * ��ʹ�ø����ռ�
     */
    public void merge_1(int[] nums1, int m, int[] nums2, int n) {

        int x = m + n - 1;
        m -= 1;
        n -= 1;
        while (m >= 0 && n >= 0) {
            nums1[x--] = nums1[m] >= nums2[n] ? nums1[m--] : nums2[n--];
        }
        /*
        ֻ��һ��ʣ�����
        m������,n��ʣ��
         */
        while (n>=0){
            nums1[n]=nums2[n--];
        }


    }

}
