package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Redirect_Out {

    public static void main(String[] args) {

        try(
                PrintStream ps = new PrintStream(new FileOutputStream("./Notes/src/main/java/IO/newFile.txt"));
                )
        {
            /**
             * ����׼����ض���Ϊps������
             * ֮���ӡ����Ϣ�������ӡ������̨
             * ���Ǵ�ӡ�ض���֮��Ĵ������У���д���ļ�
             */
            System.setOut(ps);
            System.out.println("�ض����׼�����");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
