package Algorithm.DP;

/**
 * 动态规划问题：
 * 求最大连续子序和:
 * 索引i为结尾的子序列和=索引i-1的子序列和+nums[i]
 * leetcode 53
 */
public class DynamicP_1 {
    /**
     *  为什么变负数就置为0？
     *  如果当前a，能把之前的和b降为负数，
     *  那么a之后总和c，必须满足c=b+1，才能恢复到b的和，
     *  那么也就不再需要b了，c最大了。
     */
    public static int maxSubSum1(int[] nums) {
        int maxSum = nums[0];
        int nowSum = 0;
        for (int i = 0; i < nums.length; i++) {
            nowSum = nowSum + nums[i];
            if (nowSum > maxSum) {//更新最大子段和
                maxSum = nowSum;
            }
            if (nowSum < 0) {//当当前累加和为负数时舍弃，重置为0
                nowSum = 0;
            }
        }
        return maxSum;
    }
    public static int maxSubArray1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.max(nums[i - 1] + nums[i], nums[i]);
            if (res < nums[i]) {
                res = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1};
        int max = maxSubSum1(nums);
        System.out.println(max);
    }
}
