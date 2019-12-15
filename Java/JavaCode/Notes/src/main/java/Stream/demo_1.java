package Stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
流的操作
流的原则:做什么而非怎么做
(要计数,而非先遍历,后判断,再计数)

特点:
1.流不存储元素,按需生成
2.流不会修改数据源,filter方法不会从流中移除元素,而是生成一个过滤后的新流
3.流的操作惰性执行,到达目的即停止

流的工作过程:
1.创建一个流words.stream()
2.指定将初始流转化为其他流的中间操作 filter返回一个新流
3.终止才做,产生结果w->w.length()>12

 */
public class demo_1 {

    public static void main(String[] args) throws IOException {


        String contents = new String(Files.readAllBytes(
                Paths.get("")),StandardCharsets.UTF_8);
        // \\PL+  正则表达式,以非字母分割
        List<String> words = Arrays.asList(contents.split("\\PL+"));


        // 普通迭代方法:

        long count_1 = 0;
        for (String word:words){

            if (word.length()>12) count_1++;
        }

        // 使用流:

        long count_2 = words.stream().filter(w->w.length()>12).count();
    }

}
