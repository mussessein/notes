package Net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
/*
javaʹ��InetAddress�������� IP ��ַ��

 */
public class demo_InetAddress {

    public static void main(String[] args) throws IOException {
        //��������������ȡ��Ӧ��InetAddressʵ��
        InetAddress ip=InetAddress.getByName("www.baidu.com");
        //�ж��Ƿ�ɴ�
        System.out.println("��վ�Ƿ�ɴ"+ip.isReachable(2000));
        //��ȡ��ʵ����ip����ip�ַ���
        System.out.println(ip.getHostAddress());


        //����ԭʼIP����ȡʵ��
        InetAddress local=InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println("�����Ƿ�ɴ"+local.isReachable(2000));
        //��ȡlocalʵ���Ķ�Ӧȫ�޶�����
        System.out.println(local.getCanonicalHostName());

    }
}
