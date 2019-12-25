package LeetCode;

/**
 * �ж�������,�Ƿ���ͬ
 */
public class Num_100_Tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;
        // ��ֹ��һ������Ϊ����
        if (p != null && q != null && p.val == q.val) {
            if (isSameTree(p.left, q.left) && isSameTree(p.right, q.right))
                return true;
        }
        return false;
    }

}