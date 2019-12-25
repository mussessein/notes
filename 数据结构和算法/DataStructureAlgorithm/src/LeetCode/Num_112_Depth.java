package LeetCode;

/**
 * 给定一个树sum=22
 * 找树中的路径(一个必须走到底的完整路径)是否有满足加和为sum
 * 返回true
 */
public class Num_112_Depth {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        return hasPathSum(root.left, sum - root.val)
                || hasPathSum(root.right, sum - root.val);
    }
}
