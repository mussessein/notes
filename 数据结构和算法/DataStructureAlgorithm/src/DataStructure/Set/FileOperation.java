package DataStructure.Set;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*
ʵ�֣����ļ��е����ݶ�ȡ�����������㵥�����ͷ��ظ�������
 */
public class FileOperation {

    public static boolean readFile(String filename, ArrayList<String> words) {
        if (filename == null || words == null) {
            System.out.println("filename is null or words is null!");
            return false;
        }

        //�ļ���ȡ
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
        �ִʣ�ͳ���ļ���������:
        1.���ж�ȡscanner.hasNextLine()����if�ж���ÿһ�н��д���
        2.����ȡ�����ݷ���contents
        3.����firstCharacterIndex����
        4.��ʼ�ӵ�һ����ĸ��
        �������������ĸ��i++�����������ĸ����start-i�е�������words�����У�
              ����start= firstCharacterIndex(contents, i);
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
    //Ѱ���ַ���s�У���start��ʼ��һ����ĸ�ַ���λ��
    private static int firstCharacterIndex(String s, int start) {
        for (int i = start; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i)))
                //�ҵ���ĸ��ֱ������������return��ȥ
                return i;
        }
        //���򷵻�s���ȣ����庯���е�forҲ��������
        return s.length();
    }
}
