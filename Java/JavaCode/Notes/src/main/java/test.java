import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void treeFile(File f) throws Exception {
        // 目录文件放入childs
        File[] childs = f.listFiles();
        Pattern pattern = Pattern.compile(".*create-workspace.*");
        // 遍历childs
        for (int i = 0; i < childs.length; i++) {
            File file = childs[i];
            // 检查当前child是否是文件夹
            if (!file.isDirectory()) {
                BufferedReader br = null;
                // 读取此文件
                br = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = br.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find())
                        System.out.println(file.getAbsolutePath());
                }
                if (br != null) {
                    br.close();
                    br = null;
                }
            } else {
                treeFile(childs[i]);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        File f = new File("/home/whr/Desktop/test");
        treeFile(f);
    }
}
