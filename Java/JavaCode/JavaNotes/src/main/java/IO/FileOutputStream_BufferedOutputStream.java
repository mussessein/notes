package IO;
import java.io.*;
/*
          �ļ�����(copy)
���� ��OutputStream---FileOutputStream
 */
public class FileOutputStream_BufferedOutputStream {
    
    public static void main(String[] args) throws IOException {

        // �ڵ�������
        FileInputStream fs=new FileInputStream("D:\\eclipse\\workspace\\notes\\image/a.jpg");
        FileOutputStream fo=new FileOutputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/a.jpg");
        // ����������
        BufferedInputStream bi=new BufferedInputStream(fs);
        BufferedOutputStream bo=new BufferedOutputStream(fo);

        //д�����ֽ�����������int
        int hasRead=0;
        while((hasRead=bi.read())!=-1){
            //���������ݣ�д�뻺����
            bo.write(hasRead);
            //����ȡ������ˢ�µ�Ӳ��
            bo.flush();
        }
        fs.close();
        fo.close();
    }
}
