package Offer;


/**
 * ¶þ²æÊ÷¾µÏñ
 */
public class solution_16 {
    public void Mirror(TreeNode root) {
        TreeNode tmp;
        if (root != null) {
            tmp = root.right;
            root.right = root.left;
            root.left = tmp;
            if (root.left != null) {
                Mirror(root.left);
            }
            if (root.right != null) {
                Mirror(root.right);
            }
        }
    }

    public void Mirror_1(TreeNode root) {
        TreeNode temp;
        if (root != null) {
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            if (root.left != null)
                Mirror(root.left);
            if (root.right != null)
                Mirror(root.right);
        }
    }
}

