package controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.Server;

import java.io.IOException;

public class Starter {
/*

service 端
 */
    public static void main(String[] args) throws IOException {

        RPC.Builder builder = new RPC.Builder(new Configuration());

        builder.setBindAddress("whr-PC").setPort(10000)
                .setProtocol(LoginServiceInterface.class)
                .setInstance(new LoginServiceImpl());

        // 通过配置好的builder创建server
        Server server = builder.build();
        server.start();
        // 阻塞
    }
}
