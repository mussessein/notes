package DataStructure.UnionFind;

import java.util.Random;

/*
���Բ��鼯��Ч��
 */
public class Test {

    /*
    ִ��m�κϲ���������ѯ����
     */
    public static double test(UF uf, int m) {

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            // ����������ݣ��ϲ������鼯
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            // ��ѯ�Ƿ�Ϊһ������
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int size = 10000000;
        int m = 10000000;
        /*
        ���������鼯 ���������ܴ�
        ���Ĵ����鼯 ���ܼ�������
        ��������鼯 ����·��ѹ������������
        ���������鼯 �Ż�·��ѹ�� �ٶȱ���
         */

/*      UnionFind_1 uf1 = new UnionFind_1(size);
        System.out.println("U1:" + LeetCode.bishi.Cycle_String(uf1, m));

        UnionFind_2 uf2 = new UnionFind_2(size);
        System.out.println("U2:" + LeetCode.bishi.Cycle_String(uf2, m));
*/

        UnionFind_3 uf3 = new UnionFind_3(size);
        System.out.println("UF3:" + test(uf3, m));

        UnionFind_4 uf4 = new UnionFind_4(size);
        System.out.println("UF4:" + test(uf4, m));

        UnionFind_5 uf5 = new UnionFind_5(size);
        System.out.println("UF5:" + test(uf5, m));

        UnionFind_6 uf6 = new UnionFind_6(size);
        System.out.println("UF6:" + test(uf6, m));
    }

}
