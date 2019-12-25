package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ����һ����������������ڵ�ֵ�Ե����ϵĲ�α���
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * �����
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class Num_107_tree_Queue {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * ÿ�����һ�㣬Ȼ������˲���²����нڵ㣬����list
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> oneLevel = new ArrayList<>();
            // ÿ�ζ�ȡ��һ�����������
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                oneLevel.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // ÿ�ζ�����ͷ��
            result.addFirst(oneLevel);
        }
        return result;
    }
}
