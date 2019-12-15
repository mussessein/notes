package mapreduce.InvertedIndex;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
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
 * 从StepOne继续：
 * 输入：<hello-->a.txt, 3 >..<hello-->b.txt, 4 >..<hello-->c.txt, 2 >.
 * Map输出：<hello， a.txt-->3 >..<hello， b.txt-->4 >..<hello， c.txt-->2 >.
 * Reduce输出：<hello， a.txt-->3，b.txt-->4，c.txt-->2>.。<cola， a.txt-->3，b.txt-->4，c.txt-->2>.
 */
public class InvertedIndex_StepTwo {

    public static class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {

        /*
        K:行起始偏移量 V:{hello-->a.txt   3}
        fields[0]="hello-->a.txt"
        fields[1]=3
        将fields[0]以"-->"切分为两个wordAndFilename
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // 读取每行数据
            String line = value.toString();
            String[] fields = StringUtils.split(line, " ");
            String[] wordAndFilename = StringUtils.split(fields[0], "-->");

            String word = wordAndFilename[0];
            String fileName = wordAndFilename[1];
            long count = Long.parseLong(fields[1]);

            // 此map输出结果为 <hello , a.txt-->3>
            context.write(new Text(word), new Text(fileName + "-->" + count));

        }
    }

    public static class InvertedInderReduce extends Reducer<Text, Text, Text, Text> {

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            String result = "";

            for (Text value : values) {

                result += value + "\t";
            }

            // 输出结果:<hello , a.txt-->3  flow2.txt-->2  c.txt-->4>
            context.write(key, new Text(result));

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.job.jar", "target/InvertedIndex_StepTwo.jar");
        Job job = Job.getInstance(conf);

        job.setJarByClass(InvertedIndex_StepTwo.class);

        job.setMapperClass(InvertedIndexMapper.class);
        job.setReducerClass(InvertedInderReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        // 如果output路径存在,删除
        Path output = new Path(args[1]);
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(output)) {
            fs.delete(output, true);
        }

        FileOutputFormat.setOutputPath(job, output);
        // true 表示显示运行过程
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
