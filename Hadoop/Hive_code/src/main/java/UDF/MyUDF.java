package UDF;

import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * Hive UDF自定义函数
 * 必须用evaluate方法
 */
public class MyUDF extends UDF {

    public int evaluate(int data){

        return data+5;
    }
}
