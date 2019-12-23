package IO.NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
/*
通道 Channel 是对原 I/O 包中的流的模拟，可以通过它读取和写入数据。相当于代替处理流

通道与流的不同之处在于，流只能在一个方向上移动(一个流必须是 InputStream 或者 OutputStream 的子类)
而通道是双向的，可以用于读、写或者同时用于读写。
 */
/*
      1.Channel可以用map方法，可以将指定文件全部直接映射成Buffer
      2.程序不能直接读取Channel，Channel只能通过Buffer交互，从而读取
      3.也可以不用map直接映射，也可以通过传统竹筒装水取水进行数据输入输出，详见demo_Channel
        传统方法，解码器可能会出现问题java.nio.charset.MalformedInputException: Input length = 1
        原因是中文造成的，不晓得咋解决 ！！！！！
      4.只要打印到控制台，就要进行编码，解码
 */
public class demo_Channel {
    public static void main(String[] args){
        try(
                FileInputStream fis=new FileInputStream("./src/IO/NIO/a.txt");
                FileChannel fc=fis.getChannel()
                )
        {
            //创建Buffer,CharBuffer是一个抽象类，allocate是创建一个缓冲区,分配空间
            //充当竹筒缓冲，用于取水
            ByteBuffer byteBuffer=ByteBuffer.allocate(256);
            //将fc中的数据放到buffer中
            while((fc.read(byteBuffer))!=-1){
                //每一次读取都要锁一次，目的：position和limit位置准备，读取
                byteBuffer.flip();
                //创建GBK解码器
                Charset charset=Charset.forName("GBK");
                //创建解码器对象
                CharsetDecoder decoder=charset.newDecoder();
                //使用解码器，将buffer中的内容，转化到新的charbuffer中
                CharBuffer charBuffer=decoder.decode(byteBuffer);
                System.out.println(charBuffer);
                //将buffer初始化，便于下次读取
                byteBuffer.clear();
            }
            fis.close();
            fc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
