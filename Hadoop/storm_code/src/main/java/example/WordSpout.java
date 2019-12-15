package example;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;

/*
通过Storm 完成的wordcount
 */
public class WordSpout extends BaseRichSpout {

    private SpoutOutputCollector collector;

    // 定义一个数组，来存储一些数据，发送给Bolt，模拟storm的处理流程
    private String[] lines;
    private Random random;

    /*
    初始化的方法，一些准备工作都在这里做进行初始化，只在启动的时候，调用一次
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {

        System.out.println("===============open===============");
        this.collector = spoutOutputCollector;
        // 初始化数据
        lines = new String[]{"hello world", "hadoop", "spark", "hello", "kafka"};
        random = new Random();
    }

    /*
    这个方法会一直不断地运行源源不断的获取数据，往下游发送
     */
    @Override
    public void nextTuple() {

        // 随机生成【0-lines.length）的整数
        int i = random.nextInt(lines.length);
        String line = lines[i];
        //通过collector调用emit方法，将我们的数据往下游发送
        collector.emit(new Values(line));
        //  睡眠0.2秒
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
    为我们往下游发送的数据申明一个变量值，下游可以通过这个变量值获取我们上游发送来的数据
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        //调用declare来申明我们所有发出去的字符串的一个变量，下游可以通过这个变量"stream"进行取值
        declarer.declare(new Fields("lines"));

    }
}
