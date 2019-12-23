package Net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
/*
java使用InetAddress类来代表 IP 地址，

 */
public class demo_InetAddress {

    public static void main(String[] args) throws IOException {
        //根据主机名来获取对应的InetAddress实例
        InetAddress ip=InetAddress.getByName("www.baidu.com");
        //判断是否可达
        System.out.println("网站是否可达："+ip.isReachable(2000));
        //获取该实例（ip）的ip字符串
        System.out.println(ip.getHostAddress());


        //根据原始IP来获取实例
        InetAddress local=InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println("本机是否可达："+local.isReachable(2000));
        //获取local实例的对应全限定域名
        System.out.println(local.getCanonicalHostName());

    }
}
