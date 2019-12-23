package Lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda 表达式
 * 本质:匿名方法
 * 语法:(parameters)->expression
 *     (parameters)->{expression}
 *     参数->表达式
 *
 */
public class Lambda_Stream {

    public static void main(String[] args) {
        Integer[] arr = {2, 4, 7, 9, 8, 4, 6};
        List<Integer> nums = Arrays.asList(arr);
        //使用lamabda 表达式
        nums.forEach((num) -> System.out.print(num + " "));
        //双冒号操作符
        nums.forEach(System.out::print);
        //lambda实现过滤
        System.out.println();
        nums.stream().filter(e -> e > 5 && e < 8)
                .forEach(e -> System.out.print(e + " "));
        System.out.println();

        /*
        map方法，遍历集合，并进行操作(为什么没有对nums产生影响？？？？？？)
        Stream方法返回一个流
        Stream类下的map方法，返回的是一个新的流,对原nums不产生影响，
         */
        nums.stream().map(e -> e + 10)
                .forEach(e -> System.out.print(e + " "));

        /*
        Stream是一个泛型接口,下的collect方法对流元素执行操作，
        collect(Collectors.toList())将操作后的流，返回一个新的List
         */
        List<Integer>nums2=nums.stream().map(e->e+20).collect(Collectors.toList());
        System.out.println(nums2);



        //reduce方法 对所有值进行操作，返回结果（下面是求和）
        System.out.println();
        int sum = nums.stream().reduce(
                (n1, n2) -> n1 + n2)
                .get();
        System.out.println(sum);

        /*
        IntSummaryStatistics类
        将数组返回一个流，可以处理流的各种摘要数据
         */
        IntSummaryStatistics summaryStatistics =nums.stream().mapToInt(e->e).summaryStatistics();
        System.out.println("最大值："+summaryStatistics.getMax());
        System.out.println("最小值："+summaryStatistics.getMin());
        System.out.println("平均值："+summaryStatistics.getAverage());
        System.out.println("综合："+summaryStatistics.getSum());
        System.out.println("个数："+summaryStatistics.getCount());
    }
}