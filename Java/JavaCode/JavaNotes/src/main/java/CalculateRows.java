import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateRows {
    static long classcount = 0; // Java�������
    static long blackLines = 0; // ����
    static long commentLines = 0; // ע����
    static long codeLines = 0; // ������
    static long allLines = 0; // ������


    public static void main(String[] args) throws Exception {
        File f = new File("/home/whr/Desktop/che-master"); // Ŀ¼
        String type = ".java";//����ʲô���͵Ĵ��룬��".java"���ǲ�����java�����Ĵ�����
        CalculateRows.treeFile(f, type);
        System.out.println("·����" + f.getPath());
        System.out.println(type + "��������" + classcount);
        System.out.println("����������" + codeLines);
        System.out.println("ע��������" + commentLines);
        System.out.println("����������" + blackLines);
        if (classcount == 0) {
            System.out.println("����ƽ������:" + 0);
        } else {
            System.out.println("����ƽ������:" + codeLines / classcount);
        }
        System.out.println("�� ��������" + allLines);
    }

    /**
     * ���ҳ�һ��Ŀ¼�����е�.java�ļ�
     */
    public static void treeFile(File f, String type) throws Exception {
        // Ŀ¼�ļ�����childs
        File[] childs = f.listFiles();
        Pattern pattern = Pattern.compile(".*create-workspace.*");
        // ����childs
        for (int i = 0; i < childs.length; i++) {
            File file = childs[i];
            // ��鵱ǰchild�Ƿ����ļ���
            if (!file.isDirectory()) {
                // ����Ƿ���java�ļ�
                if (file.getName().endsWith(type)) {
                    classcount++;
                    BufferedReader br = null;
                    boolean comment = false;
                    // ��ȡ���ļ�
                    br = new BufferedReader(new FileReader(file));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            System.out.println(file.getAbsolutePath());
                        }
                        allLines++;
                        line = line.trim();
                        // ��ʾ����
                        if (line.matches("^[//s&&[^//n]]*$")) {//��һ��ƥ���Կո�ͷ���������Իس�����ͷ�����Իس�����β
                            blackLines++;
                        }
                        // ��ʾ����ע�͵ĵ�һ��
                        else if (line.startsWith("/*")
                                && !line.endsWith("*/")) {//ƥ����/*......*/��ס�Ķ���ע��
                            commentLines++;
                            comment = true;
                        }
                        // ƥ�����ע�͵ĺ�����
                        else if (true == comment) {
                            commentLines++;
                            if (line.endsWith("*/")) {
                                comment = false;
                            }
                        }
                        //ƥ����//��ͷ�ĵ���ע�ͣ�����/*......*/��ס�ĵ���ע��
                        else if (line.startsWith("//") || (line.startsWith("/*") && line.endsWith("*/"))) {
                            commentLines++;
                        }
                        // ƥ�������
                        else {
                            codeLines++;
                        }
                    }
                    if (br != null) {
                        br.close();
                        br = null;
                    }
                }
            } else {
                treeFile(childs[i], type);
            }
        }
    }
}