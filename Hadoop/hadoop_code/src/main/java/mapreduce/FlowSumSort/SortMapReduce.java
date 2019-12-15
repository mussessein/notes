package mapreduce.FlowSumSort;

import mapreduce.FlowSum.FlowBean;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 自定义排序，前提是在JavaBean中重写compareTo方法
 */
public class SortMapReduce  {

    public static class SortMapper extends Mapper<LongWritable,Text, FlowBean, NullWritable>{

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line =value.toString();
            String[] fields = StringUtils.split(line,"\t");

            String phone =fields[0];
            long up_flow = Long.parseLong(fields[1]);
            long down_flow = Long.parseLong(fields[2]);
            // 封装数据kv,并输出(K:JavaBean对象；V：空，表示没有输出)
            context.write(new FlowBean(phone,up_flow,down_flow),NullWritable.get());
        }
    }

    public static class SortReduce extends Reducer<FlowBean,NullWritable,Text,FlowBean>{

        @Override
        protected void reduce(FlowBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

            String phone=key.getPhone();
            context.write(new Text(phone),key);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.job.jar", "target/SortMapReduce.jar");

        Job job = Job.getInstance(conf);

        job.setJarByClass(SortMapReduce.class);

        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReduce.class);
        // map输出
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        // reduce输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        // true 表示显示运行过程
        job.waitForCompletion(true);
    }

}
