package DataStructure.Set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*
实现，将文件中的内容读取出来，并计算单词数和非重复单词数
 */
public class FileOperation {

    public static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("filename is null or words is null!");
            return false;
        }

        //文件读取
        Scanner scanner;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            } else
                return false;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open " + filename);
            return false;
        }
        /*
        分词：统计文件单词数量:
        1.逐行读取scanner.hasNextLine()，在if中对于每一行进行处理
        2.将读取的内容放入contents
        3.调用firstCharacterIndex方法
        4.开始从第一个字母，
        遍历：如果是字母，i++，如果不是字母，从start-i切掉，放入words链表中，
              重置start= firstCharacterIndex(contents, i);
         */
        if (scanner.hasNextLine()) {
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length();) {
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    words.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
            }
            return true;
        }
        else
            return false;
    }
    //寻找字符串s中，从start开始第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                //找到字母，直接跳出函数，return出去
                return i;
        }
        //否则返回s长度，主体函数中的for也不会运行
        return s.length();
    }
}
