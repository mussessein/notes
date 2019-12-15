package Stream;

import java.util.Arrays;
import java.util.stream.Stream;

/*
map
filter
flatmap
抽取子流:
limit:返回前n个元素的流
skip:返回跳过前n过元素的流
连接流:
concat
去除重复元素:
distinct
排序:
sorted

返回集合
collect(Collectors.toList())
 */
public class Stream_Method {
    public static void main(String[] args) {

        Stream<String> words = Stream.of("Gently", "Down", "The", "Stream");
//
        // 全部转换小写
//        List<String> lowercaseWords = words.map(String::toLowerCase).collect(Collectors.toList());
//        System.out.println(lowercaseWords);
        // 拿到每个单词的首字母
//        List<String> firstLetters =words.map(s->s.substring(0,1)).collect(Collectors.toList());
//        System.out.println(firstLetters);

        // 查询是否含有单词
        boolean hasword = words.parallel().anyMatch(s -> s.equals("The"));


        String[] strs = {"aaa", "bbb", "ccc"};
        Arrays.stream(strs).map(str -> str.split("")).forEach(System.out::println);
        Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).forEach(System.out::println);
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(System.out::println);

        // 实现排序
//        List<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
//        System.out.println(longestFirst);


    }
}
