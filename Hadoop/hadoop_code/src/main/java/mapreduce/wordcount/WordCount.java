package mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.io.IOException;


public class WordCount {

    /*
    用来描述一个作业(Job)
    比如：作业使用哪个类作为处理的map，reduce
    指定作业要处理的数据所在路径
    制定作业输出的存放路径
    (如果项目中有xml即：运行在hdfs文件系统中的路径)
    （如果项目没有xml，即可运行在本地：本地运行，就不需要启动Yarn，纯java跑MapReduce）
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        /*
        1.配置对象
        2.作业对象
          指定主函数入口(jar包位置)
        3.拿到之前写的mapper，reduce类
        4.指定mapper的输出 key,value 类型
        5.指定要统计的文件位置
        6.指定输出结果的存放路径（此路径文件夹不可以存在）(HDFS的文件系统的路径，非本地路径)
        7.提交到集群运行waitForCompletion
         */
        Configuration conf = new Configuration();
        conf.set("mapreduce.job.jar","target/hadoop_code-1.0-SNAPSHOT.jar");
        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCount.class);

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));

        // 如果output路径存在,删除
        Path output = new Path(args[1]);
        FileSystem fs =FileSystem.get(conf);
        if (fs.exists(output)){
            fs.delete(output,true);
        }

        FileOutputFormat.setOutputPath(job, output);
        // true 表示显示运行过程
        job.waitForCompletion(true);

    }
}
