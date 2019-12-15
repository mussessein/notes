package string;

/**
 * һ�����뻺������ȡ��ֻ��ȡ�ַ�����
 *
 * ���ֽ�����ת��Ϊ�ַ����ķ���
 */
class Buffer_test_1 {
    public static void main(String[] args) {
//��һ��int���飬��Ϊ�ַ���,���ַ������Ż���ͬ
        int[] arr = {1, 2, 4, 6, 7};
        String s1 = ArrayToString_1(arr);
        String s2 = ArrayToString_2(arr);
        System.out.println(s1);
        System.out.println(s2);
    }

    //ʹ��Strig��ת����ÿ�ζ��ᴴ���µ��ַ����������������
    private static String ArrayToString_1(int[] arr) {
        String str = "[";
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                str += arr[i] + ",";
            } else {
                str += arr[i] + "]";
            }
        }
        return str;
    }

    //ʹ��StrigBuilder��ת����ֻ����һ���ַ�����������Ӷ���,���������������
    private static String ArrayToString_2(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                sb.append(arr[i] + ",");
            } else {
                sb.append(arr[i] + "]");
            }
        }
        return sb.toString();
    }

}