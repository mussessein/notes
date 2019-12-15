package IO;

import java.io.*;

/*
        File类
0.将文件或者文件夹封装成对象
1.对于文件和目录的操作都是使用File类来操作的
2.File能新建/删除/重命名文件和目录
3.File不能访问文件内容本身，如果要访问，需要输入/输出流。
 */
public class demo_File {

    public static void main(String[] args) throws IOException {

        //通过路径字符串来创建File实例
        File file=new File("."); // "."表示当前路径
        //返回File对象表示的文件名或路径名
        System.out.println(file.getName());
        //获取相对路径的父路径
        System.out.println("相对路径的父路径:"+file.getParent());
        //获得绝对路径
        System.out.println("绝对路径"+file.getAbsoluteFile());
        //获得上一级路径
        System.out.println("上一级路径"+file.getAbsoluteFile().getParent());
        //在当前路径下创建一个临时文件
        File tmpFile=File.createTempFile("aaa",".txt",file);
        //指定当JVM退出时，删除该文件
        tmpFile.deleteOnExit();
        //将file对象路径下的所有文件，放在String[]中,然后遍历输出当前路径所有文件
        String[] fileList=file.list();
        System.out.println("--------当前路径下所有文件和路径如下-------");
        for (String fileName:fileList){
            System.out.println(fileName);
        }
        //打印系统根路径
        File[] roots=File.listRoots();
        System.out.println("--------------系统所有根路径如下-----------");
        for (File root:roots){
            System.out.println(root);
        }
    }
}
