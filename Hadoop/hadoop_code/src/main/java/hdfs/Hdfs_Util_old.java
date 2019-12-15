package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hdfs_Util_old {

    public static void main(String[] args) throws IOException {

        /*
        从hadoop下载文件(底层写法)
        1.需要一个配置文件(hdfs-site.xml，core-site.xml需要放到本项目中读取)
        2.需要一个hadoop的路径
        3.打开文件到输入流
        4.通过输出流写数据到本地
         */
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);
        Path src = new Path("hdfs://master:9000/upload/saber.jpg");
        FSDataInputStream fins = fs.open(src);
        FileOutputStream fouts = new FileOutputStream("/home/whr/Desktop/saber.jpg");
        IOUtils.copyBytes(fins,fouts,conf);
    }
}
