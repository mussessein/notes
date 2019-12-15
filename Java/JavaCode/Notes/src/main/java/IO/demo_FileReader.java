package IO;

import java.io.*;
/*
顶层父类 InputStream /  Reader
fis.read(b);将fis读到的内容存入b中
 */
public class demo_FileReader {

    public static void main(String[] args) throws IOException {

        //创建字节输入流,读出文件，打印出来
        FileInputStream fis= new FileInputStream("./Notes/src/main/java/IO/text/1.txt");
        //创建一个字节数组存放读取过来的数据
        byte[] b=new byte[1024];
        //创建一个变量，代表，当前读到的字节数
        int hasRead=0;
        //循环输出读到的内容
        while(0 < (hasRead = fis.read(b))){
            //将读到的字节数组，转换成字符串输出打印
            System.out.print(new String(b,0,hasRead));
        }
        fis.close();

        //创建字符输入流
        try(
                FileReader fr=new FileReader("./Notes/src/main/java/IO/text/1.txt");
                )
        {
            //创建长度为32的字符数组
            char[] c=new char[32];
            // int hasRead_2=0;
            while ((hasRead=fr.read(c))>0){
                System.out.print(new String(c,0,hasRead));
            }

        }catch (IOException ioe){ioe.printStackTrace();}
    }

}
