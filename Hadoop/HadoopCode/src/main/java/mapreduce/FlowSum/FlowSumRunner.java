package mapreduce.FlowSum;

import java.io.IOException;

import mapreduce.wordcount.WordCount;
import mapreduce.wordcount.WordCountMapper;
import mapreduce.wordcount.WordCountReducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


/*
统计手机流量的MapReduce
 */
//public class FlowSumRunner extends Configured implements Tool {
//    // job描述和提交的规范写法
//    @Override
//    public int run(String[] args) throws Exception {
//
//        Configuration conf = new Configuration();
//        Job job = Job.getInstance(conf);
//
//        job.setJarByClass(FlowSumRunner.class);
//
//        job.setMapperClass(FlowSumMapper.class);
//        job.setReducerClass(FlowSumReduce.class);
//
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(FlowBean.class);
//
//        FileInputFormat.setInputPaths(job, new Path(args[0]));
//        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//
//        return job.waitForCompletion(true) ? 0 : 1;
//    }
//
//    public static void main(String[] args) throws Exception {
//        // 结果：0正常；1异常
//        int res = ToolRunner.run(new Configuration(), new FlowSumRunner(), args);
//        System.exit(res);
//    }
//}

// 本地启动
public class FlowSumRunner {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		conf.set("mapreduce.job.jar", "target/FlowSum.jar");
		Job job = Job.getInstance(conf);

		job.setJarByClass(FlowSumRunner.class);
		job.setMapperClass(FlowSumMapper.class);
		job.setReducerClass(FlowSumReduce.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// true 表示显示运行过程
		job.waitForCompletion(true);
	}
}
