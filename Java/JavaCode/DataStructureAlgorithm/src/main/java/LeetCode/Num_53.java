package LeetCode;

/*
����һ���������� nums
�ҵ�һ���������͵����������飨���������ٰ���һ��Ԫ�أ������������͡�

˼·:

 */
public class Num_53 {
/*
������ԶԽ��Խ��,sum>0�������,�ͼ���ȥ
����Խ��ԽС,sum<0�Ļ�,ֻȡ������
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
