package mapreduce.wordcount;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text,Text, LongWritable> {
    /*
    <LongWritable, Text,Text, IntWritable>
    Mapper传入四个泛型：都是hadoop自己封装的类型：本质上是<Long，String,String,Long>
    前两个数据，是指定mapper的输入数据的类型
     */

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        /*
        map：具体的业务逻辑方法体(只负责统计，不负责计算)
        传入的形参：key-value
        key是这一行数据的起始偏移量，value是这一行的文本内容
        context：存放输出
        */

        /*
        步骤：
        1.拿到value文本内容
        2.切分单词StringUtiles->hadoop.common.lang3
        3.遍历单词,封装进context
         */
        String line = value.toString();
        String[] words = StringUtils.split(line," ");
        for (String word:words){
            // 初始计数为 1---> <单词，1>
            context.write(new Text(word),new LongWritable(1)); //封装传给reduce

        }


    }
}
