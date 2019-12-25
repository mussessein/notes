package Offer;

/*
����ĳ��������ǰ���������������Ľ�������ؽ����ö�������
���������ǰ���������������Ľ���ж������ظ������֡�
��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}��
���ؽ������������ء�
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution_4 {


    /**
     * pre[]�е�һ������һ���Ǹ��ڵ�
     * in[]�еĸ��ڵ����ߣ�ȫ�����������ұߣ�ȫ��������
     */

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = new TreeNode(pre[0]);
        build(root, pre, 0, pre.length, in, 0, in.length);
        return root;
    }

    /**
     * �ݹ�Ͷ���˼�룬�����ⲻ�ϻ��֣�ֱ���������׽����
     * �����ǣ�����һ�����ڵ㣬��ȥ�����������ҵ����ڵ��ֵ����λ�ã��������λ�÷ֳ�2���֣��󲿷ֵ��������г��ȼ�Ϊǰ���������󲿷ֵ��������г��ȣ��Ҳ�����Ȼ��
     * Ȼ��ʼ���������⣬������г���Ϊ0����Ҫ���������⡣��������ǰ�����е�һ��Ԫ��Ϊ���ڵ���������ɸ��ڵ㣬Ȼ���������⡣
     *
     * @param root ���ڵ�
     * @param pre  ǰ������ ��Χ��[pleft,pright)
     * @param in   �������� ��Χ��[ileft,iright)
     */
    public void build(TreeNode root, int[] pre, int pleft, int pright, int[] in, int ileft, int iright) {
        int i;
        for (i = ileft; i < iright; i++) {
            if (in[i] == root.val) {//����������Ѱ�Ҹ��ڵ��λ��
                break;
            }
        }
        int t = i - ileft;
        if (t > 0) {//��������Ϊ0ʱ��������������
            root.left = new TreeNode(pre[pleft + 1]);
            build(root.left, pre, pleft + 1, pleft + 1 + t, in, ileft, i);
        }

        if (pright - pleft - 1 - t > 0) {
            root.right = new TreeNode(pre[pleft + 1 + t]);
            build(root.right, pre, pleft + 1 + t, pright, in, i + 1, iright);
        }
    }

    // ǰ�����
    public static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // �������
    public static void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        preOrder(root);
        System.out.println("==============");
        inOrder(root);
    }
}
