import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void treeFile(File f) throws Exception {
        // Ŀ¼�ļ�����childs
        File[] childs = f.listFiles();
        Pattern pattern = Pattern.compile(".*create-workspace.*");
        // ����childs
        for (int i = 0; i < childs.length; i++) {
            File file = childs[i];
            // ��鵱ǰchild�Ƿ����ļ���
            if (!file.isDirectory()) {
                BufferedReader br = null;
                // ��ȡ���ļ�
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
