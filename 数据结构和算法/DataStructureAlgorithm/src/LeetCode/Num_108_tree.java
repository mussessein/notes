package LeetCode;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树
 * [-10,-3,0,5,9]
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Num_108_tree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        int high = nums.length - 1;
        int low = 0;

        TreeNode head = createTree(nums, low, high);
        return head;
    }

    private static TreeNode createTree(int[] nums, int low, int high) {
        if (nums.length == 0 || low > high) {
            return null;
        }
        int mid = low + (high - low) / 2;
        TreeNode res = new TreeNode(nums[mid]);
        res.right = createTree(nums, mid + 1, high);
        res.left = createTree(nums, low, mid - 1);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        sortedArrayToBST(nums);
    }
}
