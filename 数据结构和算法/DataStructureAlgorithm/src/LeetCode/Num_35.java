package LeetCode;

/*
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

并不进行插入操作
 */
public class Num_35 {
    public int searchInsert(int[] nums, int target) {

        int low = 0;
        int high = nums.length-1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (target < nums[mid]) high = mid - 1;
            else if (target > nums[mid]) low = mid + 1;
            else return mid;
        }
        return low;
    }

    public int searchInsert1(int[] nums, int target) {
        int sta = 0;
        int end = nums.length;
        int mid = (sta + end) / 2;
        while (sta <= end && mid >= 0 && mid <= nums.length - 1) {
            if (target > nums[mid]) {
                sta = mid + 1;
                mid = (sta + end) / 2;
            } else if (target < nums[mid]) {
                end = mid - 1;
                mid = (sta + end) / 2;
            } else if (target == nums[mid]) {
                return mid;
            }
        }
        return sta;
    }

    public static void main(String[] args) {
        int[] nums={1,2,3,4,5};
        int res =new Num_35().searchInsert(nums,8);
        System.out.println(res);
    }
}
