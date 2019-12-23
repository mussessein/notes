package string;

/**
 * 一旦存入缓冲区，取，只能取字符串；
 *
 * 两种将数组转化为字符串的方法
 */
class Buffer_test_1 {
    public static void main(String[] args) {
//将一个int数组，变为字符串,两种方法，优化不同
        int[] arr = {1, 2, 4, 6, 7};
        String s1 = ArrayToString_1(arr);
        String s2 = ArrayToString_2(arr);
        System.out.println(s1);
        System.out.println(s2);
    }

    //使用Strig来转换，每次都会创建新的字符串，制造更多垃圾
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

    //使用StrigBuilder来转换，只存在一个字符串，不断添加而已,不会产生多余垃圾
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