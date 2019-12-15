package LeetCode;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地(不使用额外空间)对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 */
public class Num_75_QuikSort {
    /**
     * 计算有多少个0,1,2,然后重新赋值
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
     * 快速排序思想：
     * 三指针，
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
