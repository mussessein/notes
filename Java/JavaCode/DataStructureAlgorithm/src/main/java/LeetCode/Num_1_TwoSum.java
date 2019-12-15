package LeetCode;

import java.util.*;

/**
 * 找到数组内两数之和满足要求的所有组合
 * a+b=0
 */

public class Num_1_TwoSum {
    /*
    ①暴力解：
    */
    public static List<List<Integer>> twoSum_1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[i], nums[j]));
                }
            }
        }
        return res;
    }

    /*
    哈希表:内存换时间，增加了内存的消耗。
     */
    public static List<List<Integer>> twoSum_2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res.add(Arrays.asList(i, map.get(target - nums[i])));
            } else {
                map.put(target - nums[i], i);
            }
        }
        return res;
    }
    /*
    提交的方法：7ms
     */
    public int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                arr[1] = i;
                arr[0] = map.get(nums[i]);
                return arr;
            } else {
                map.put(target - nums[i], i);
            }
        }
        return null;
    }


    public static void main(String[] args) {

        System.out.println(twoSum_1(new int[]{-1, 0, 8, 1, -8, 0, 9}, 0));

    }
}
