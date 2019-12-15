package LeetCode;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 */

public class Num_110_tree {


    boolean res = true;

    public boolean isBalanced(TreeNode root) {

        search(root);
        return res;

    }

    private int search(TreeNode root) {
        if (root == null) return 0;
        int left = search(root.left) + 1;
        int right = search(root.right) + 1;
        if (Math.abs(right - left) > 1) res = false;
        return Math.max(left, right);
    }
}
