package Stream;

import java.util.Arrays;
import java.util.List;

/*
并行流parallelStream
迭代器只能命令式地、串行化操作。顾名思义，当使用串行方式去遍历时，每个 item 读完后再读下一个 item
而使用并行去遍历时，数据会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出
 */
public class ParallelStream {

    public static void main(String[] args) {

        // 此代码得到的不一定是按照列表的顺序
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);
    }
}
