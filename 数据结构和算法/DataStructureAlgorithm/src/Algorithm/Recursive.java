package Algorithm;

/*
        �ܶ���֮��һ���ݹ����������֣������ʹ����ֿ����ǣ�
        �������ǵݹ鵽�����ô��
        ��������εݹ飬������ж���������Ҫ��������
�ݹ麯������ͣ�
    1.��װ�˵ݹ麯��
    2.������һ��s �������䵱����
    3.��return��������
 */
public class Recursive {
    //���� arr[s....n]�����ֺ�
    public static int sum(int[] arr) {

        return sum(arr, 0);
    }

    // ����arr���飬��s��ʼ���
    private static int sum(int[] arr, int s) {
        if (s == arr.length)
            return 0;
        return arr[s] + sum(arr, s + 1); //arr[0]+sum(arr[1-n])
    }

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3};
        System.out.println(Recursive.sum(arr1));
    }
}
