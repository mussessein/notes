package LeetCode;

/**
 * 给定一个二叉树，找出其最小深度。
 * 空树不能计为1
 * 比如：【1,2】，应该返回2
 */
public class Num_111_MinDeepth {
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0)
            return right + 1;
        if (right == 0)
            return left + 1;
        else
            return Math.min(left, right) + 1;
    }
}
