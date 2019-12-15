package UDF;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

public class MyUDTF extends GenericUDTF {
    private List<String> dataList = new ArrayList<>();

    /**
     * 定义输出数据的列名和数据类型
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs)
            throws UDFArgumentException {
        // 定义输出数据的列名
        List<String> fieldName = new ArrayList<>();
        fieldName.add("word");
        // 定义输出数据的类型
        List<ObjectInspector> fieldOIs = new ArrayList<>();
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        //
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldName, fieldOIs);
    }

    /**
     * 函数处理逻辑:函数需要两个参数：
     * 1.args[0]：一个字符数组，
     * 2.args[1]：字符数组的分隔符
     * 使用方法：select myudtf('hello,word,qqq,new',',');
     */
    @Override
    public void process(Object[] args) throws HiveException {
        /**
         * 1.获取数据
         * 2.获取分隔符
         * 3.切分数据
         * 4.输出数据
         */
        String data = args[0].toString();
        String splitKey = args[1].toString();
        String[] words = data.split(splitKey);
        for (String word : words) {
            dataList.clear();
            dataList.add(word);
            forward(dataList);
        }
    }

    @Override
    public void close() throws HiveException {
    }
}
