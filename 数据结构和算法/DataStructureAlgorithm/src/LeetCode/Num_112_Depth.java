package LeetCode;

/**
 * ����һ����sum=22
 * �����е�·��(һ�������ߵ��׵�����·��)�Ƿ�������Ӻ�Ϊsum
 * ����true
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
