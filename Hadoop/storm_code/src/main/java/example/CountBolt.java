package example;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/*
第二个Bolt 用于统计词频
 */
public class CountBolt extends BaseBasicBolt {

    // 定义数据存储在Map中
    private static Map<String, Integer> map = new HashMap<String, Integer>();

    /*
    execute方法会反复的执行
    */
    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        //获取上游发送的数据
        String word = tuple.getStringByField("word");
        Integer nums = tuple.getIntegerByField("nums");
        /**
         * 使用map存储数据
         */
        if (map.containsKey(word)) {
            map.put(word, map.get(word) + nums);
        } else {
            map.put(word, nums);
        }
        System.err.println(Thread.currentThread().getId() + "  word:" + word + "  num:" + map.get(word));
    }

    /*
    没有下游,不需要实现此方法
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
