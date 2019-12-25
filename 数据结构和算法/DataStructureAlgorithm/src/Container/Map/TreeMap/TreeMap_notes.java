package Container.Map.TreeMap;

import java.util.Map;
import java.util.TreeMap;

/**
 * µ×²ãºìºÚÊ÷£¨ÅÅĞò£¬²»¿ÉÖØ¸´£©
 * put,get,remove,clear,containKey,containValue
 */
public class TreeMap_notes {
    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>();
        //treeMap.put(null, "d");  //±¨´í£¡£¡£¡
        treeMap.put("1", "a");
        treeMap.put("2", "c");
        treeMap.put("3", "b");
        treeMap.put("4", "d");
        String lastKey = treeMap.lastKey();
        String firstKey = treeMap.firstKey();
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {

        }
        boolean isContain = treeMap.containsValue("a");


    }

}
