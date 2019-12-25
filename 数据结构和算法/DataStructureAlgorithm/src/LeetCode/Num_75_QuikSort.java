package LeetCode;

import java.util.Arrays;

/**
 * ����һ��������ɫ����ɫ����ɫ��һ�� n ��Ԫ�ص����飬
 * ԭ��(��ʹ�ö���ռ�)�����ǽ�������ʹ����ͬ��ɫ��Ԫ�����ڣ������պ�ɫ����ɫ����ɫ˳�����С�
 * ����: [2,0,2,1,1,0]
 * ���: [0,0,1,1,2,2]
 */
public class Num_75_QuikSort {
    /**
     * �����ж��ٸ�0,1,2,Ȼ�����¸�ֵ
     */
    public static void sortColors_1(int[] nums) {
        int index=0;
        int[] sort = new int[3];
        for (int i = 0; i < nums.length; i++) {
            index=nums[i]%3;
            sort[index]=sort[index]+1;
        }
        int i=0;
        int j=0;
        while (i<3){
            int k = sort[i];
            while (k>0){
                nums[j]=i;
                j++;
                k--;
            }
            i++;
        }
    }

    /**
     * ��������˼�룺
     * ��ָ�룬
     */
    public static void sortColors_2(int[] nums) {
        int i = 0;
        int left = 0;
        int right = nums.length - 1;
        while (i <= right) {
            if (nums[i] == 0) {
                int tmp = nums[i];
                nums[i++] = nums[left];
                nums[left++] = tmp;
            } else if (nums[i] == 2) {
                int tmp = nums[i];
                nums[i] = nums[right];
                nums[right--] = tmp;
            } else {
                i++;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors_2(nums);
        System.out.println(Arrays.toString(nums));
    }
}
