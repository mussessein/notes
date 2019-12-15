package LeetCode;

/**
 * 给定一个二叉树，找出其最大深度。
 */
public class Num_104_Tree_H {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int maxDepth(TreeNode root) {
        int h1=0;
        int h2=0;

        return Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
