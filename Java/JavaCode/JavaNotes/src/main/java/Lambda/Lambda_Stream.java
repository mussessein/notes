package Lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * lambda ���ʽ
 * ����:��������
 * �﷨:(parameters)->expression
 *     (parameters)->{expression}
 *     ����->���ʽ
 *
 */
public class Lambda_Stream {

    public static void main(String[] args) {
        Integer[] arr = {2, 4, 7, 9, 8, 4, 6};
        List<Integer> nums = Arrays.asList(arr);
        //ʹ��lamabda ���ʽ
        nums.forEach((num) -> System.out.print(num + " "));
        //˫ð�Ų�����
        nums.forEach(System.out::print);
        //lambdaʵ�ֹ���
        System.out.println();
        nums.stream().filter(e -> e > 5 && e < 8)
                .forEach(e -> System.out.print(e + " "));
        System.out.println();

        /*
        map�������������ϣ������в���(Ϊʲôû�ж�nums����Ӱ�죿����������)
        Stream��������һ����
        Stream���µ�map���������ص���һ���µ���,��ԭnums������Ӱ�죬
         */
        nums.stream().map(e -> e + 10)
                .forEach(e -> System.out.print(e + " "));

        /*
        Stream��һ�����ͽӿ�,�µ�collect��������Ԫ��ִ�в�����
        collect(Collectors.toList())�����������������һ���µ�List
         */
        List<Integer>nums2=nums.stream().map(e->e+20).collect(Collectors.toList());
        System.out.println(nums2);



        //reduce���� ������ֵ���в��������ؽ������������ͣ�
        System.out.println();
        int sum = nums.stream().reduce(
                (n1, n2) -> n1 + n2)
                .get();
        System.out.println(sum);

        /*
        IntSummaryStatistics��
        �����鷵��һ���������Դ������ĸ���ժҪ����
         */
        IntSummaryStatistics summaryStatistics =nums.stream().mapToInt(e->e).summaryStatistics();
        System.out.println("���ֵ��"+summaryStatistics.getMax());
        System.out.println("��Сֵ��"+summaryStatistics.getMin());
        System.out.println("ƽ��ֵ��"+summaryStatistics.getAverage());
        System.out.println("�ۺϣ�"+summaryStatistics.getSum());
        System.out.println("������"+summaryStatistics.getCount());
    }
}