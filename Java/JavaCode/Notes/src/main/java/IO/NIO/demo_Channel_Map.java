package IO.NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
/*
      1.Channel可以用map方法，可以将指定文件全部直接映射成Buffer
      2.程序不能直接读取Channel，Channel只能通过Buffer交互，从而读取
      3.也可以不用map直接映射，也可以通过传统竹筒装水取水进行数据输入输出，详见demo_Channel
        传统方法，解码器可能会出现问题java.nio.charset.MalformedInputException: Input length = 1
        原因是中文造成的，不晓得咋解决 ！！！！！
      4.只要打印到控制台，就要进行编码，解码
 */
public class demo_Channel_Map {

    public static void main(String[] args){

        File f=new File("./src/IO/NIO/demo_Channel_Map.java");
        try(
                //以节点流创建输入流，并传入文件f
                FileChannel inChannel=new FileInputStream(f).getChannel();
                //以节点流创建输出流，输出到a.txt
                FileChannel outChannel=new FileOutputStream("./src/IO/NIO/a.txt").getChannel();
                )
        {
            //将输入流中的文件，通过map方法，全部映射成ByteBuffer
            MappedByteBuffer byteBuffer=inChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //将buffer中的内容输出到指定文件
            outChannel.write(byteBuffer);
            //复原limit，position位置，以便于再次打印出来
            byteBuffer.clear();
            //创建GBK解码器
            Charset charset=Charset.forName("GBK");
            //创建解码器对象
            CharsetDecoder decoder=charset.newDecoder();
            //使用解码器，将buffer中的内容，转化到新的charbuffer中
            CharBuffer charBuffer=decoder.decode(byteBuffer);
            //打印解码后charbuffer中的内容，toString方法转化为字符串
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
