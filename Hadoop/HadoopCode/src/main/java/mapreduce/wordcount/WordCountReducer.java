package mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


import java.io.IOException;

public class WordCountReducer extends Reducer<Text,LongWritable, Text,LongWritable> {
/*
在mapper处理完成之后，将所有的kv对，缓存起来，进行分组，然后传递一个组<key,value[]>
形成<hello,[1,1,1,1]>的过程就是shuffle过程！！！

（Text key, Iterable<LongWritable> values）
这个参数 就是 <hello,[1,1,1,1]>
然后这个K-v调用下面的方法，统计 1 的个数，也就是单词频率
在封装成：<hello,4>，输出最终结果
 */
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        // key是单词；value是一个list[1,1,1,1,...]
        long count = 0;
        for (LongWritable value : values) {

            count += value.get();
        }
        // 一对一对的输出新的KV
        context.write(key,new LongWritable(count));// 输出<hello,4>
    }
}
