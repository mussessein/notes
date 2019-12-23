package IO;
import java.io.*;
/*
顶层父类 OutputStream / Writer
fis.read(b);将fis读到的内容存入b中

 */
public class demo_FileWriter {
    public static void main(String[] args){
        //字节流，先读取，后写入
        try(
                //创建字节流输入流（读）
                FileInputStream fis=new FileInputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/1.txt");
                //创建字节输出流（写）
                FileOutputStream fos=new FileOutputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/2.txt");)
        {
            //自定义的缓冲区,读到的内容缓存在这里
            byte[] b=new byte[32];
            // 读取的长度,调试可以查看
            int hasRead=0;
            //循环读取数据，因为是字节流，一次能读取的内容有限
            while((hasRead=fis.read(b))>0){
                //write方法，长度为hasRead的字节写入b字节数组中，
                fos.write(b,0,hasRead);
            }

        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){e.printStackTrace();}


        //字符流 直接写文件，没有读取过程
        String s="锦瑟\r\n" +
                "锦瑟无端五十弦，一弦一柱思华年。\r\n" +
                "庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n" +
                "沧海月明珠有泪，蓝田日暖玉生烟。\r\n" +
                "此情可待成追忆？只是当时已惘然。\r\n";
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
