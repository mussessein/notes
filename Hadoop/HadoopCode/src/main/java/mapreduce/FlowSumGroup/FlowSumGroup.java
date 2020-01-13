package mapreduce.FlowSumGroup;

import mapreduce.FlowSum.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 自定义分组，按照省份对手机号进行分区：
 * 1.自定义partitioner
 * 2.自定义reduce task的并发任务数
 */
public class FlowSumGroup {


    public static class FlowSumGroupMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // line= 一行数据
            String line = value.toString();
            // 数据集txt，是\t分割的
            String[] fields = StringUtils.split(line, " ");
            // 每行数据的第一个是手机号
            String phone = fields[0];
            // 第八位是上行流量
            long up_flow = Long.parseLong(fields[7]);
            // 第九位下行流量
            long down_flow = Long.parseLong(fields[8]);

            // 封装数据kv,K：手机号；V：JavaBean
            context.write(new Text(phone), new FlowBean(phone, up_flow, down_flow));

        }
    }

    public static class FlowSumGroupReduce extends Reducer<Text, FlowBean, Text, FlowBean> {
        /*
         每传递一组数据<13688887777,FlowBean>,调用一次reduce方法
         reduce方法进行便利value,累加求和,然后输出
         */
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            /**
             * Text:手机号
             * key:JavaBean对象
             * values：所有手机号相同的JavaBean对象的迭代器，遍历values，统计同一手机号的总流量
             */
            long up_flow_counter = 0;
            long down_flow_counter = 0;
            // 遍历,计算流量
            for (FlowBean bean : values) {
                up_flow_counter += bean.getUp_flow();
                down_flow_counter += bean.getDown_flow();
            }
            // 输出
            context.write(key, new FlowBean(key.toString(), up_flow_counter, down_flow_counter));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.job.jar", "target/FlowSumGroup.jar");
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowSumGroup.class);
        job.setMapperClass(FlowSumGroupMapper.class);
        job.setReducerClass(FlowSumGroupReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 重写的排序方法
        job.setPartitionerClass(AreaPartitioner.class);
        // 设置reduce的并发任务数量，跟分组数保持一致
        job.setNumReduceTasks(5);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true)?0:1);
    }
}
