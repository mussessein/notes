package IO.NIO;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
/*
      1.Channel������map���������Խ�ָ���ļ�ȫ��ֱ��ӳ���Buffer
      2.������ֱ�Ӷ�ȡChannel��Channelֻ��ͨ��Buffer�������Ӷ���ȡ
      3.Ҳ���Բ���mapֱ��ӳ�䣬Ҳ����ͨ����ͳ��Ͳװˮȡˮ��������������������demo_Channel
        ��ͳ���������������ܻ��������java.nio.charset.MalformedInputException: Input length = 1
        ԭ����������ɵģ�������զ��� ����������
      4.ֻҪ��ӡ������̨����Ҫ���б��룬����
 */
public class demo_Channel_Map {

    public static void main(String[] args){

        File f=new File("./src/IO/NIO/demo_Channel_Map.java");
        try(
                //�Խڵ����������������������ļ�f
                FileChannel inChannel=new FileInputStream(f).getChannel();
                //�Խڵ�������������������a.txt
                FileChannel outChannel=new FileOutputStream("./src/IO/NIO/a.txt").getChannel();
                )
        {
            //���������е��ļ���ͨ��map������ȫ��ӳ���ByteBuffer
            MappedByteBuffer byteBuffer=inChannel.map(FileChannel.MapMode.READ_ONLY,0,f.length());
            //��buffer�е����������ָ���ļ�
            outChannel.write(byteBuffer);
            //��ԭlimit��positionλ�ã��Ա����ٴδ�ӡ����
            byteBuffer.clear();
            //����GBK������
            Charset charset=Charset.forName("GBK");
            //��������������
            CharsetDecoder decoder=charset.newDecoder();
            //ʹ�ý���������buffer�е����ݣ�ת�����µ�charbuffer��
            CharBuffer charBuffer=decoder.decode(byteBuffer);
            //��ӡ�����charbuffer�е����ݣ�toString����ת��Ϊ�ַ���
            System.out.println(charBuffer);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
