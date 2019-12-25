package LeetCode;

/*
����һ�����������һ��Ŀ��ֵ�����������ҵ�Ŀ��ֵ����������������
���Ŀ��ֵ�������������У����������ᱻ��˳������λ�á�

�������в������
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
