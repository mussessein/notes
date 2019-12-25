package LeetCode;

/*
给定一个整数数组 nums
找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

思路:

 */
public class Num_53 {
/*
正数永远越加越大,sum>0的情况下,就加下去
负数越加越小,sum<0的话,只取单个数
 */
    public int maxSubArray(int[] nums) {
        int sum=0;
        int res=nums[0];
        for(int num:nums){
            sum=sum>0?sum+num:num;
            if(res<sum){
                res=sum;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums1={-1,-2};
        int[] nums2={-1};
        int[] nums3={-2,1};
        int res = new Num_53().maxSubArray(nums1);
        System.out.println(res);
    }
}
