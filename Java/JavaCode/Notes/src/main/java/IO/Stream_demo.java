package IO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

/**
 * Stream/IntStream/LongStream/DoubleStream ��ʽAPI��ÿ�ν��в����������Զ�ִ��close����������ÿ��ֻ�ܲ���һ�Ρ�
 * ��������ֻ�������� Int//Long//Double
 * 
 */
class Stream_demo {

    public static void main(String[] args) {

        IntStream is1 = IntStream.builder() //ֻ�ܲ���һ��
            .add(20)
            .add(40)
            .add(7)
            .add(9)
            .build();
        /*System.out.println("is1 ����Ԫ�ص����ֵ�� " + is1.max().getAsInt());
        System.out.println("is1 ����Ԫ�ص���Сֵ�� " + is1.min().getAsInt());
        System.out.println("is1 ����Ԫ�ص��ܺ� ��" + is1.sum());
        System.out.println("is1 ����Ԫ�ص���������" + is1.count());
        //predicate��������
        System.out.println("is1 ����Ԫ�صĴ�С�Ƿ񶼴���10 ��" + is1.allMatch(ele -> ele > 10));
        System.out.println("is1 �Ƿ��������20��Ԫ�� ��" + is1.anyMatch(ele -> ele == 20)); //allMatch��ʾ�б����У�anyMatch��ʾ��������
        */
        is1.forEach(System.out::println);

//��Stream�Լ�����Ԫ�ؽ�����������
        Collection<String> c1 = new ArrayList<String>();
        c1.add("abc123");
        c1.add("def4562222");
        c1.add("abc4561111111111");
        //��predicate���������ϣ�ͳ�Ƴ��ȴ���10���ַ�������
        System.out.println("���ȴ���10���ַ�������Ϊ�� "+c1.stream().filter(ele->((String)ele).length()>10).count());
        System.out.println("����'a'���ַ����������� "+c1.stream().filter(ele->((String)ele).contains("a")).count());
    }
}