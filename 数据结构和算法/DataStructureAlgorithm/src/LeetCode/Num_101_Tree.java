package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Num_101_Tree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 递归写
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return match(root,root);
    }
    private boolean match(TreeNode l, TreeNode r) {
        if (l==null&&r==null)
            return true;
        else if (l==null||r==null)
            return false;
        else{
            return l.val == r.val && match(l.left, r.right) && match(l.right, r.left);
        }

    }

    /**
     * 队列,比较慢
     */
    public boolean isSymmetric_1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }


}
