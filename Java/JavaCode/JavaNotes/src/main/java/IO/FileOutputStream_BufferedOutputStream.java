package IO;
import java.io.*;
/*
          文件操作(copy)
父类 ：OutputStream---FileOutputStream
 */
public class FileOutputStream_BufferedOutputStream {
    
    public static void main(String[] args) throws IOException {

        // 节点流对象
        FileInputStream fs=new FileInputStream("D:\\eclipse\\workspace\\notes\\image/a.jpg");
        FileOutputStream fo=new FileOutputStream("D:\\eclipse\\workspace\\notes\\src\\IO\\text/a.jpg");
        // 处理流对象
        BufferedInputStream bi=new BufferedInputStream(fs);
        BufferedOutputStream bo=new BufferedOutputStream(fo);

        //写的是字节流，所以是int
        int hasRead=0;
        while((hasRead=bi.read())!=-1){
            //读到的内容，写入缓冲区
            bo.write(hasRead);
            //缓冲取得内容刷新到硬盘
            bo.flush();
        }
        fs.close();
        fo.close();
    }
}
