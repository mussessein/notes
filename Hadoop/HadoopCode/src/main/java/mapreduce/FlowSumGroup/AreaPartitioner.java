package mapreduce.FlowSumGroup;

import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 *  排序逻辑重写
 *
 */
public class AreaPartitioner<K,V> extends Partitioner<K,V> {
    private static HashMap<String, Integer> areaMap = new HashMap<String, Integer>();
    static{
        areaMap.put("131",0);
        areaMap.put("132",1);
        areaMap.put("133",2);
        areaMap.put("134",3);
        areaMap.put("135",4);

    }
    @Override
    public int getPartition(K key, V value, int i) {

        Integer areaCode=areaMap.get(key.toString().substring(0,3));
        if (null!=areaCode){
            return areaCode;
        }
        return 5;
    }
}
