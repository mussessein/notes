package Stream;

import java.util.Arrays;
import java.util.List;

/*
������parallelStream
������ֻ������ʽ�ء����л�����������˼�壬��ʹ�ô��з�ʽȥ����ʱ��ÿ�� item ������ٶ���һ�� item
��ʹ�ò���ȥ����ʱ�����ݻᱻ�ֳɶ���Σ�����ÿһ�����ڲ�ͬ���߳��д���Ȼ�󽫽��һ�����
 */
public class ParallelStream {

    public static void main(String[] args) {

        // �˴���õ��Ĳ�һ���ǰ����б��˳��
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEach(System.out::println);
    }
}
