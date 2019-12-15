package Offer;

/*
输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
则重建二叉树并返回。
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
     * pre[]中第一个数，一定是根节点
     * in[]中的根节点的左边，全是左子树，右边，全是右子树
     */

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root = new TreeNode(pre[0]);
        build(root, pre, 0, pre.length, in, 0, in.length);
        return root;
    }

    /**
     * 递归和二分思想，将问题不断划分，直到问题容易解决。
     * 做法是：对于一个根节点，先去中序序列中找到根节点的值所在位置，利用这个位置分成2部分，左部分的中序序列长度即为前序序列中左部分的中序序列长度，右部分亦然。
     * 然后开始生成子问题，如果序列长度为0则不需要生成子问题。否则：利用前序序列第一个元素为根节点的性质生成根节点，然后构造子问题。
     *
     * @param root 根节点
     * @param pre  前序序列 范围是[pleft,pright)
     * @param in   中序序列 范围是[ileft,iright)
     */
    public void build(TreeNode root, int[] pre, int pleft, int pright, int[] in, int ileft, int iright) {
        int i;
        for (i = ileft; i < iright; i++) {
            if (in[i] == root.val) {//从中序序列寻找根节点的位置
                break;
            }
        }
        int t = i - ileft;
        if (t > 0) {//子树长度为0时不必生成子问题
            root.left = new TreeNode(pre[pleft + 1]);
            build(root.left, pre, pleft + 1, pleft + 1 + t, in, ileft, i);
        }

        if (pright - pleft - 1 - t > 0) {
            root.right = new TreeNode(pre[pleft + 1 + t]);
            build(root.right, pre, pleft + 1 + t, pright, in, i + 1, iright);
        }
    }

    // 前序遍历
    public static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
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
