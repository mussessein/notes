package IO;
import java.io.*;
/*
          ʵ�����룬��ӡ��ת������

     ����      OutputStream
              FileOutputStream
      ����     PrintStream
 */
public class demo_PrintStream {

    public static void main(String[] args) throws IOException {

        try(
                //��System.in����ת��ΪReader����
                InputStreamReader isRead=new InputStreamReader(System.in);
                //��ͨReader��װΪ�����������ֽ���ת��Ϊ�ַ���
                BufferedReader br=new BufferedReader(isRead);
                )
        {
            String line=null;
            //ѭ����ȡ
            while((line=br.readLine())!=null){
                //�������quit�����˳�
                if(line.equals("quit")){
                    System.out.println("Bye :)");
                    System.exit(1);
                }
                System.out.println("���������Ϊ�� "+line);
            }
        }catch (IOException e){e.printStackTrace();}
    }

}
