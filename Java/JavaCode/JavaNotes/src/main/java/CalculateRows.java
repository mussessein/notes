import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateRows {
    static long classcount = 0; // Java类的数量
    static long blackLines = 0; // 空行
    static long commentLines = 0; // 注释行
    static long codeLines = 0; // 代码行
    static long allLines = 0; // 代码行


    public static void main(String[] args) throws Exception {
        File f = new File("/home/whr/Desktop/che-master"); // 目录
        String type = ".java";//查找什么类型的代码，如".java"就是查找以java开发的代码量
        CalculateRows.treeFile(f, type);
        System.out.println("路径：" + f.getPath());
        System.out.println(type + "类数量：" + classcount);
        System.out.println("代码数量：" + codeLines);
        System.out.println("注释数量：" + commentLines);
        System.out.println("空行数量：" + blackLines);
        if (classcount == 0) {
            System.out.println("代码平均数量:" + 0);
        } else {
            System.out.println("代码平均数量:" + codeLines / classcount);
        }
        System.out.println("总 行数量：" + allLines);
    }

    /**
     * 查找出一个目录下所有的.java文件
     */
    public static void treeFile(File f, String type) throws Exception {
        // 目录文件放入childs
        File[] childs = f.listFiles();
        Pattern pattern = Pattern.compile(".*create-workspace.*");
        // 遍历childs
        for (int i = 0; i < childs.length; i++) {
            File file = childs[i];
            // 检查当前child是否是文件夹
            if (!file.isDirectory()) {
                // 检查是否是java文件
                if (file.getName().endsWith(type)) {
                    classcount++;
                    BufferedReader br = null;
                    boolean comment = false;
                    // 读取此文件
                    br = new BufferedReader(new FileReader(file));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            System.out.println(file.getAbsolutePath());
                        }
                        allLines++;
                        line = line.trim();
                        // 表示空行
                        if (line.matches("^[//s&&[^//n]]*$")) {//这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾
                            blackLines++;
                        }
                        // 表示多行注释的第一行
                        else if (line.startsWith("/*")
                                && !line.endsWith("*/")) {//匹配以/*......*/括住的多行注释
                            commentLines++;
                            comment = true;
                        }
                        // 匹配多行注释的后续行
                        else if (true == comment) {
                            commentLines++;
                            if (line.endsWith("*/")) {
                                comment = false;
                            }
                        }
                        //匹配以//开头的单行注释，及以/*......*/括住的单行注释
                        else if (line.startsWith("//") || (line.startsWith("/*") && line.endsWith("*/"))) {
                            commentLines++;
                        }
                        // 匹配代码行
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