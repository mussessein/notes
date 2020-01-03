package controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import java.io.IOException;
import java.net.InetSocketAddress;

public class LoginController {

    /*
    远程调用服务RPC
    两台服务器A，B，一个应用部署在A服务器上，想要调用B服务器上应用提供的函数/方法，由于不在一个内存空间，
   不能直接调用，需要通过网络来表达调用的语义和传达调用的数据.
    service 在其他主机上，通过此方法，远程调用
    service和controller无论在哪，所属包名必须一致！！！！
     */
    public static void main(String[] args) throws IOException {

        // 创建service接口实例
        LoginServiceInterface proxy=RPC.getProxy(
                LoginServiceInterface.class, 1L, new InetSocketAddress("whr-PC", 10000), new Configuration());

        String result =proxy.login("haha","123");
        System.out.println(result);

    }
}
