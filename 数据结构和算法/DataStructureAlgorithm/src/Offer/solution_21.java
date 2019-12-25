package Offer;

/**
 * 判断是否是BST的后序遍历
 * BST的后序遍历比较有规律；、
 * 最后一个是根节点，
 * 前一半是左树（小于根节点），
 * 后一半是右树（大于根节点）；
 */
public class solution_21 {
    public static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0)
            return false;
        int size = sequence.length - 1;
        while (size > 0) {
            int i = 0;
            while (sequence[i++] < sequence[size]) ;
            i--;
            while (sequence[i++] > sequence[size]) ;
            i--;
            if (i != size)
                return false;
            size--;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] sequence = {2, 4, 3, 6, 8, 7, 5};
        boolean b = VerifySquenceOfBST(sequence);
        System.out.println(b);
    }
}
