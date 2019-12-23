package Stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/*
���Ĳ���
����ԭ��:��ʲô������ô��
(Ҫ����,�����ȱ���,���ж�,�ټ���)

�ص�:
1.�����洢Ԫ��,��������
2.�������޸�����Դ,filter��������������Ƴ�Ԫ��,��������һ�����˺������
3.���Ĳ�������ִ��,����Ŀ�ļ�ֹͣ

���Ĺ�������:
1.����һ����words.stream()
2.ָ������ʼ��ת��Ϊ���������м���� filter����һ������
3.��ֹ����,�������w->w.length()>12

 */
public class demo_1 {

    public static void main(String[] args) throws IOException {


        String contents = new String(Files.readAllBytes(
                Paths.get("")),StandardCharsets.UTF_8);
        // \\PL+  ������ʽ,�Է���ĸ�ָ�
        List<String> words = Arrays.asList(contents.split("\\PL+"));


        // ��ͨ��������:

        long count_1 = 0;
        for (String word:words){

            if (word.length()>12) count_1++;
        }

        // ʹ����:

        long count_2 = words.stream().filter(w->w.length()>12).count();
    }

}
