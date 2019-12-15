package Summary;

public class Judge_BST {
    /**
     * 判断是否是BST的后序遍历
     */
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

    /**
     * 判断是否为前序遍历
     */

    public static void main(String[] args) {
        int[] sequence = {2, 4, 3, 6, 8, 7, 5};
        boolean b = VerifySquenceOfBST(sequence);
        System.out.println(b);
    }
}

