package Summary;

public class Rebuild_Tree {
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
}