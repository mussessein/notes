package Algorithm;

/*
        总而言之：一个递归代码分两部分：遍历和处理，分开考虑！
        处理：就是递归到最后，怎么办
        遍历：如何递归，如果有判断条件，需要考虑条件
递归函数的求和：
    1.封装了递归函数
    2.定义了一个s 变量，充当索引
    3.在return中完成相加
 */
public class Recursive {
    //计算 arr[s....n]的数字和
    public static int sum(int[] arr) {

        return sum(arr, 0);
    }

    // 计算arr数组，从s开始求和
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
