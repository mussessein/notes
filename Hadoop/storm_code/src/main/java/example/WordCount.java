package example;


import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

import java.util.concurrent.TimeUnit;

public class WordCount {
    /**
     * 拓扑主函数
     * 主程序的入口,将Spout和Bolt组织起来
     */
    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException, InterruptedException {

        // 通过TopologyBuilder组装 Spout和Bolt
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("wordSpout", new WordSpout(), 6);
        builder.setBolt("splitBolt", new SplitBolt(), 6).globalGrouping("wordSpout");
        builder.setBolt("countBolt", new CountBolt(), 8).globalGrouping("splitBolt");

        /**
         * storm程序的运行有两种方式，一种是集群方式运行，一种是本地模式运行
         */
        Config config = new Config();
        // 指定当前topology 需要的worker的数量
        config.setNumWorkers(3);

        if (null != args && args.length > 0) {
            // 集群模式
            config.setDebug(false);
            StormTopology topology = builder.createTopology();
            StormSubmitter.submitTopology(args[0], config, topology);
        } else {
            // 本地模式
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("localStorm", config, builder.createTopology());

            // 运行10秒退出
            TimeUnit.SECONDS.sleep(10);
            // 关闭此Topology
            cluster.killTopology("localStorm");
            cluster.shutdown();
        }
    }
}
