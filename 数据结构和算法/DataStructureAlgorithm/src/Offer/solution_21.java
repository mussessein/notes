package Offer;

/**
 * �ж��Ƿ���BST�ĺ������
 * BST�ĺ�������Ƚ��й��ɣ���
 * ���һ���Ǹ��ڵ㣬
 * ǰһ����������С�ڸ��ڵ㣩��
 * ��һ�������������ڸ��ڵ㣩��
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
