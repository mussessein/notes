package IO;
import java.io.*;
/*
���㸸�� OutputStream / Writer
fis.read(b);��fis���������ݴ���b��

 */
public class demo_FileWriter {
    public static void main(String[] args){
        //�ֽ������ȶ�ȡ����д��
        try(
                //�����ֽ���������������
                FileInputStream fis=new FileInputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/1.txt");
                //�����ֽ��������д��
                FileOutputStream fos=new FileOutputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/2.txt");)
        {
            //�Զ���Ļ�����,���������ݻ���������
            byte[] b=new byte[32];
            // ��ȡ�ĳ���,���Կ��Բ鿴
            int hasRead=0;
            //ѭ����ȡ���ݣ���Ϊ���ֽ�����һ���ܶ�ȡ����������
            while((hasRead=fis.read(b))>0){
                //write����������ΪhasRead���ֽ�д��b�ֽ������У�
                fos.write(b,0,hasRead);
            }

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}


        //�ַ��� ֱ��д�ļ���û�ж�ȡ����
        String s="��ɪ\r\n" +
                "��ɪ�޶���ʮ�ң�һ��һ��˼���ꡣ\r\n" +
                "ׯ�������Ժ��������۴����жž顣\r\n" +
                "�׺����������ᣬ������ů�����̡�\r\n" +
                "����ɴ���׷�䣿ֻ�ǵ�ʱ���Ȼ��\r\n";
        try(
                FileWriter fw=new FileWriter("D:\\eclipse\\workspace\\notes\\src\\IO\\newFile.txt");
                )
        {
            fw.write(s);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
