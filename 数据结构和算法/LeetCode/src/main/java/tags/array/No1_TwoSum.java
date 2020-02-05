package tags.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whr
 * @date 2019/12/25 9:24
 * @Description 两数之和
 */
public class No1_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                res[0] = map.get(nums[i]);
                res[1] = i;
            } else {
                map.put(target - nums[i], i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 2, 3, 6};
        int[] res = twoSum(arr, 7);
        System.out.println(Arrays.toString(res));
    }
}
