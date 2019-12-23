package IO;
import java.io.*;
/*
Ϊ��������ݶ�д��Ч�ʣ�����ʹ�û������������Լ�����������
���ࣺ       Writer     /      Reader
����      BufferedWriter  /   BufferedReader
����                      /   LineNumberReader��ʵ���к���������ҳ䵱������
�ṩ�ˣ�      ��дread����������
                readline����    һ�ζ�һ�У�����\n ���з�����ȡ�����������´ζ�ȡ

Writer����һ��flush��)������ǿ��ˢ�»���ȥ������ǰ�����������ݣ�ǿ��д��
Ϊ�˷�ֹ��������û����������£�һֱ���ڵȴ���״̬�������Ч��
 */
public class FileReader_BufferedReader {

    public static void main(String[] args)throws IOException{

        //���� �ַ�������������
        FileReader fr=new FileReader("D:\\eclipse\\workspace\\notes\\src\\IO\\text/1.txt");
        BufferedReader br=new BufferedReader(fr);

        //���� �ַ������������
        FileWriter fw=new FileWriter("D:\\eclipse\\workspace\\notes\\src\\IO\\text/2.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        //����һ��lineNumberReader����ʵ������кţ����ҳ䵱����
        LineNumberReader lr=new LineNumberReader(fr);

        // �ַ����Ļ�����,readLine����,����String
        String line=null;
        //�����ݶ���line��
        while((line=lr.readLine())!=null){
            //��ȡ��ǰ�к�
            System.out.println(lr.getLineNumber()+":"+line);
            //�����������ݣ�line��д�뻺����
            bw.write(line);
            //����
            bw.newLine();
            //ǿ��ˢ�»��壬ǿ�ƻ�����д��Ӳ��
            bw.flush();
        }
        br.close();
        bw.close();
        /*
                              ��ʹ��readline������д��
        int ch=0;
        while((ch=br.read())!=-1){
        bw.write(ch);
        bw.flush();
        }

         */
    }
}
