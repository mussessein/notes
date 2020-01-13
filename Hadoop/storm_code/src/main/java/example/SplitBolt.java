package example;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/*
定义两个Bolt，这是其中一个
用于切分单词
 */
public class SplitBolt extends BaseBasicBolt {
    /*
    execute方法会反复的执行
     */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
        //获取上游发送的数据
        final String lines = tuple.getStringByField("lines");
        // 切分逻辑
        String[] split = lines.split(" ");

        // 继续发送到下游
        for (String word:split){
            collector.emit(new Values(word,1));
        }

    }

    /*
    继续发送下游
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

        // 发送两个流
        declarer.declare(new Fields("word","nums"));
    }
}
