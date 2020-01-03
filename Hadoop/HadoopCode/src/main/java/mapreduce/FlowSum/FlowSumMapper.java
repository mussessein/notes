package mapreduce.FlowSum;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
FlowBean是自定义类
要在hadoop的各个节点之间传输,应该遵循hadoop的序列化机制
FlowBean必须实现相应的序列化接口Writable
 */
public class FlowSumMapper extends Mapper<LongWritable, Text,Text,FlowBean> {

    /*
     拿到日志中的一行数据
     切分各个字段,抽取出我们需要的字段:手机,流量
     然后封装成KV发送出去
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // line= 一行数据
        String line = value.toString();
        // 数据集txt，是\t分割的
        String[] fields= StringUtils.split(line," ");
        // 每行数据的第一个是手机号
        String phone =fields[0];
        // 第八位是上行流量
        long up_flow = Long.parseLong(fields[7]);
        // 第九位下行流量
        long down_flow = Long.parseLong(fields[8]);
        // 封装数据kv,K：手机号；V：JavaBean
        context.write(new Text(phone),new FlowBean(phone,up_flow,down_flow));

    }
}
