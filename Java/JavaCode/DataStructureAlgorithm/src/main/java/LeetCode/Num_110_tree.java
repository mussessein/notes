package LeetCode;

/**
 * ����һ�����������ж����Ƿ��Ǹ߶�ƽ��Ķ�������
 * һ��������ÿ���ڵ� ���������������ĸ߶Ȳ�ľ���ֵ������1��
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
