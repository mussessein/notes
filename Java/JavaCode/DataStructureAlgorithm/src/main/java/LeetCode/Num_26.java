package LeetCode;

import java.util.Arrays;

/*
给定一个排序数组，
你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
(方法：快慢双指针)
在原数组中进行操作。
空间复杂度O（1）
时间复杂度O（n）
 */
public class Num_26 {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args){

        int[] nums = {1,1,1,2};
        new Num_26().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
