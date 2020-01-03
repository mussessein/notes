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
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;

/**
 * 倒排索引步骤一job
 * 多个文件进行wordcount，并最后输出hello：a.txt-->2,b.txt-->3.....
 * Map输出：<hello-->a.txt, 1 >..<hello-->b.txt, 1 >..<hello-->c.txt, 1 >..
 * Reduce输出：<hello-->a.txt, 3 >..<hello-->b.txt, 4 >..<hello-->c.txt, 2 >.
 */
public class InvertedIndex_StepOne {

    public static class InvertedIndexMapper extends Mapper<LongWritable, Text,Text,LongWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // 读取每行数据
            String line =value.toString();
            String[] fields = StringUtils.split(line," ");

            /**
             * 拿到切片信息,FileSplit是InputSplit的一个实现类
             * 拿到文件名
             */
            FileSplit fileSplit =  (FileSplit) context.getInputSplit();
            String filename = fileSplit.getPath().getName();

            for (String field:fields){
                // <hello-->a.txt, 1 >..
                context.write(new Text(field+"-->"+filename),new LongWritable(1));
            }
        }
    }

    public static class InvertedInderReduce extends Reducer<Text,LongWritable,Text,LongWritable>{

        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long count=0;
            for (LongWritable value:values){

                count+=value.get();
            }
            context.write(key,new LongWritable(count));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("mapreduce.job.jar", "target/InvertedIndex_StepOne.jar");
        Job job = Job.getInstance(conf);

        job.setJarByClass(InvertedIndex_StepOne.class);

        job.setMapperClass(InvertedIndexMapper.class);
        job.setReducerClass(InvertedInderReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        // 如果output路径存在,删除
        Path output = new Path(args[1]);
        FileSystem fs =FileSystem.get(conf);
        if (fs.exists(output)){
            fs.delete(output,true);
        }
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, output);
        // true 表示显示运行过程
        System.exit(job.waitForCompletion(true)?0:1);
    }

}
