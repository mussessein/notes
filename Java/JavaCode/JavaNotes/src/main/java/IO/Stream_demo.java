package IO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

/**
 * Stream/IntStream/LongStream/DoubleStream 流式API，每次进行操作，都会自动执行close（）；所以每次只能操作一次。
 * 返回类型只有三个： Int//Long//Double
 * 
 */
class Stream_demo {

    public static void main(String[] args) {

        IntStream is1 = IntStream.builder() //只能操作一次
            .add(20)
            .add(40)
            .add(7)
            .add(9)
            .build();
        /*System.out.println("is1 所有元素的最大值： " + is1.max().getAsInt());
        System.out.println("is1 所有元素的最小值： " + is1.min().getAsInt());
        System.out.println("is1 所有元素的总和 ：" + is1.sum());
        System.out.println("is1 所有元素的总数量：" + is1.count());
        //predicate参数遍历
        System.out.println("is1 所有元素的大小是否都大于10 ：" + is1.allMatch(ele -> ele > 10));
        System.out.println("is1 是否包含等于20的元素 ：" + is1.anyMatch(ele -> ele == 20)); //allMatch表示判别所有，anyMatch表示任意满足
        */
        is1.forEach(System.out::println);

//用Stream对集合中元素进行批量操作
        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc123");
        c1.add("def4562222");
        c1.add("abc4561111111111");
        //在predicate遍历基础上，统计长度大于10的字符串数量
        System.out.println("长度大于10的字符串数量为： "+c1.stream().filter(ele->((String)ele).length()>10).count());
        System.out.println("含有'a'的字符串的数量： "+c1.stream().filter(ele->((String)ele).contains("a")).count());
    }
}