package mapreduce.MaxTemperature;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
/*
在hadoop集群上运行这个作业，要把代码打包成一个jar文件
 */

public class MaxTemperature {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Usage: MaxTemperature <inputOne path> <output path>");
            System.exit(-1);
        }
        // 构造Job对象，制定输入输出数据的路径
        Job job = new Job();
        job.setJarByClass(MaxTemperature.class);
        job.setJobName("Max temperature");

        // 定义输入数据的路径，可以有多个
        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 制定输出路径，只能有一个，在运行之前，该目录不可以存在
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 制定要使用的map类，reduce类
        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        // 控制reduce函数的输出类型，
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // waitForCompletion提交作业，并等待执行完成，01标识表示成功失败
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
