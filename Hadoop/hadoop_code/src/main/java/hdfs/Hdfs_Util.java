package hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Hdfs_Util {

    // hdsf的客户端事例，可以调用各种hdfs方法的一个对象
    FileSystem fs = null;

    @Before  // 其他方法运行之前运行
    public void inti() throws Exception {
    /*
        1.读取classpath下的xxx.site.xml文件，解析，封装进conf对象中
        2.conf.set()，可以设置配置文件，并覆盖本地的配置文件
     */
        Configuration conf = new Configuration();
        //conf.set(""."");
        fs = FileSystem.get(conf);
    }
    /*
    上传文件
    1.需要configuration（需要set设置映射地址,将core-sit-xml放到项目中就不需要设置了）
    2.文件对象读取configuration
    3.设置hadoop目录
    4.输出流创建文件
    5.输入流读取本地文件
    */
    @Test
    public void upload() {

        try {

            Path dir = new Path("hdfs://master:9000/upload/saber.jpg");
            FSDataOutputStream os = fs.create(dir);
            FileInputStream is = new FileInputStream("/home/whr/Pictures/ACG/saber.jpg");
            IOUtils.copy(is, os);
        }catch (Exception e){e.printStackTrace();}
    }

    @Test
    public void upload_2() throws IOException {

        Path dst = new Path("hdfs://master:9000/upload/Hadoop_error.txt");
        Path local = new Path("/home/whr/Desktop/Hadoop_notes/hadoop_error.txt");
        fs.copyFromLocalFile(dst,local);
    }
    /*
    下载文件
     */
    @Test
    public void download() throws IOException {
        Path dst = new Path("hdfs://whr-PC:9000/upload/Hadoop_notes.md");
        Path local = new Path("/home/whr/Desktop/hadoop_notes.txt");
        fs.copyToLocalFile(dst,local);
    }

    /*
    查看文件信息（非文件内容）
    */
    @Test
    public void listFile_1() throws IOException {
        /*
        true:递归
        1.拿到路径信息 传给 路径对象
        2.递归拿到当前目录及子目录下的所有文件file,不显示文件夹
         */
        RemoteIterator<LocatedFileStatus> files = fs.listFiles(new Path("/"), true);

        while(files.hasNext()){
            // 文件对象
            LocatedFileStatus file = files.next();
            Path filePath = file.getPath();
            String filename = filePath.getName();
            System.out.println(filename);
        }
    }
    @Test
    public void listfile_2() throws IOException {
        /*
        第二种方法：只能拿到当前目录下的文件和文件夹 dir
         */
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus status:listStatus){

            String name = status.getPath().getName();
            System.out.println(name);
        }

    }
    /*
    创建文件目录
    */
    @Test
    public void mkdir() throws IOException {

        fs.mkdirs(new Path("/newdir/new"));

    }
    /*
    删除文件
     */
    @Test
    public void delete() throws IOException {

        // true 递归删除，子目录也删除
        fs.delete(new Path("/newdir"),true);
    }

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);
        FSDataInputStream is = fs.open(new Path("hdfs://hadoop1:9000/test/inputOne/a.txt"));
        FileOutputStream os = new FileOutputStream("C:\\Users\\whr\\Desktop\\");
        IOUtils.copy(is,os);
    }

}
