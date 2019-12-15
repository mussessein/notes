package IO.NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
/*
ͨ�� Channel �Ƕ�ԭ I/O ���е�����ģ�⣬����ͨ������ȡ��д�����ݡ��൱�ڴ��洦����

ͨ�������Ĳ�֮ͬ�����ڣ���ֻ����һ���������ƶ�(һ���������� InputStream ���� OutputStream ������)
��ͨ����˫��ģ��������ڶ���д����ͬʱ���ڶ�д��
 */
/*
      1.Channel������map���������Խ�ָ���ļ�ȫ��ֱ��ӳ���Buffer
      2.������ֱ�Ӷ�ȡChannel��Channelֻ��ͨ��Buffer�������Ӷ���ȡ
      3.Ҳ���Բ���mapֱ��ӳ�䣬Ҳ����ͨ����ͳ��Ͳװˮȡˮ��������������������demo_Channel
        ��ͳ���������������ܻ��������java.nio.charset.MalformedInputException: Input length = 1
        ԭ����������ɵģ�������զ��� ����������
      4.ֻҪ��ӡ������̨����Ҫ���б��룬����
 */
public class demo_Channel {
    public static void main(String[] args){
        try(
                FileInputStream fis=new FileInputStream("./src/IO/NIO/a.txt");
                FileChannel fc=fis.getChannel()
                )
        {
            //����Buffer,CharBuffer��һ�������࣬allocate�Ǵ���һ��������,����ռ�
            //�䵱��Ͳ���壬����ȡˮ
            ByteBuffer byteBuffer=ByteBuffer.allocate(256);
            //��fc�е����ݷŵ�buffer��
            while((fc.read(byteBuffer))!=-1){
                //ÿһ�ζ�ȡ��Ҫ��һ�Σ�Ŀ�ģ�position��limitλ��׼������ȡ
                byteBuffer.flip();
                //����GBK������
                Charset charset=Charset.forName("GBK");
                //��������������
                CharsetDecoder decoder=charset.newDecoder();
                //ʹ�ý���������buffer�е����ݣ�ת�����µ�charbuffer��
                CharBuffer charBuffer=decoder.decode(byteBuffer);
                System.out.println(charBuffer);
                //��buffer��ʼ���������´ζ�ȡ
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
