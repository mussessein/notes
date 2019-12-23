package Stream;

import java.util.Arrays;
import java.util.stream.Stream;

/*
map
filter
flatmap
��ȡ����:
limit:����ǰn��Ԫ�ص���
skip:��������ǰn��Ԫ�ص���
������:
concat
ȥ���ظ�Ԫ��:
distinct
����:
sorted

���ؼ���
collect(Collectors.toList())
 */
public class Stream_Method {
    public static void main(String[] args) {

        Stream<String> words = Stream.of("Gently", "Down", "The", "Stream");
//
        // ȫ��ת��Сд
//        List<String> lowercaseWords = words.map(String::toLowerCase).collect(Collectors.toList());
//        System.out.println(lowercaseWords);
        // �õ�ÿ�����ʵ�����ĸ
//        List<String> firstLetters =words.map(s->s.substring(0,1)).collect(Collectors.toList());
//        System.out.println(firstLetters);

        // ��ѯ�Ƿ��е���
        boolean hasword = words.parallel().anyMatch(s -> s.equals("The"));


        String[] strs = {"aaa", "bbb", "ccc"};
        Arrays.stream(strs).map(str -> str.split("")).forEach(System.out::println);
        Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).forEach(System.out::println);
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(System.out::println);

        // ʵ������
//        List<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
//        System.out.println(longestFirst);


    }
}
