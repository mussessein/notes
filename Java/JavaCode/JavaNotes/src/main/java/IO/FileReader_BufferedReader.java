package IO;
import java.io.*;
/*
为了提高数据读写的效率，可以使用缓冲区，而非自己创建缓冲区
父类：       Writer     /      Reader
子类      BufferedWriter  /   BufferedReader
子类                      /   LineNumberReader：实现行号输出，并且充当缓冲区
提供了：      重写read（）方法，
                readline（）    一次读一行，遇到\n 换行符，读取结束，继续下次读取

Writer下有一个flush（)方法，强制刷新缓冲去，将当前缓冲区的内容，强制写入
为了防止缓冲区在没有满的情况下，一直处于等待的状态，提高了效率
 */
public class FileReader_BufferedReader {

    public static void main(String[] args)throws IOException{

        //创建 字符输入流缓冲区
        FileReader fr=new FileReader("D:\\eclipse\\workspace\\notes\\src\\IO\\text/1.txt");
        BufferedReader br=new BufferedReader(fr);

        //创建 字符输出流缓冲区
        FileWriter fw=new FileWriter("D:\\eclipse\\workspace\\notes\\src\\IO\\text/2.txt");
        BufferedWriter bw=new BufferedWriter(fw);
        //创建一个lineNumberReader对象，实现输出行号，并且充当缓冲
        LineNumberReader lr=new LineNumberReader(fr);

        // 字符流的缓冲区,readLine方法,返回String
        String line=null;
        //将内容读到line中
        while((line=lr.readLine())!=null){
            //获取当前行号
            System.out.println(lr.getLineNumber()+":"+line);
            //将读到的内容（line）写入缓冲区
            bw.write(line);
            //换行
            bw.newLine();
            //强制刷新缓冲，强制缓冲区写入硬盘
            bw.flush();
        }
        br.close();
        bw.close();
        /*
                              不使用readline方法的写法
        int ch=0;
        while((ch=br.read())!=-1){
        bw.write(ch);
        bw.flush();
        }

         */
    }
}
