package LeetCode;

/**
 * ����һ�����������ҳ�����С��ȡ�
 * �������ܼ�Ϊ1
 * ���磺��1,2����Ӧ�÷���2
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
