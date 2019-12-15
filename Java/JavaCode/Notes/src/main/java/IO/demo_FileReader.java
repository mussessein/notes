package IO;

import java.io.*;
/*
���㸸�� InputStream /  Reader
fis.read(b);��fis���������ݴ���b��
 */
public class demo_FileReader {

    public static void main(String[] args) throws IOException {

        //�����ֽ�������,�����ļ�����ӡ����
        FileInputStream fis= new FileInputStream("./Notes/src/main/java/IO/text/1.txt");
        //����һ���ֽ������Ŷ�ȡ����������
        byte[] b=new byte[1024];
        //����һ��������������ǰ�������ֽ���
        int hasRead=0;
        //ѭ���������������
        while(0 < (hasRead = fis.read(b))){
            //���������ֽ����飬ת�����ַ��������ӡ
            System.out.print(new String(b,0,hasRead));
        }
        fis.close();

        //�����ַ�������
        try(
                FileReader fr=new FileReader("./Notes/src/main/java/IO/text/1.txt");
                )
        {
            //��������Ϊ32���ַ�����
            char[] c=new char[32];
            // int hasRead_2=0;
            while ((hasRead=fr.read(c))>0){
                System.out.print(new String(c,0,hasRead));
            }

        }catch (IOException ioe){ioe.printStackTrace();}
    }

}
