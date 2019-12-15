package IO;

import java.io.*;

/*
        File��
0.���ļ������ļ��з�װ�ɶ���
1.�����ļ���Ŀ¼�Ĳ�������ʹ��File����������
2.File���½�/ɾ��/�������ļ���Ŀ¼
3.File���ܷ����ļ����ݱ������Ҫ���ʣ���Ҫ����/�������
 */
public class demo_File {

    public static void main(String[] args) throws IOException {

        //ͨ��·���ַ���������Fileʵ��
        File file=new File("."); // "."��ʾ��ǰ·��
        //����File�����ʾ���ļ�����·����
        System.out.println(file.getName());
        //��ȡ���·���ĸ�·��
        System.out.println("���·���ĸ�·��:"+file.getParent());
        //��þ���·��
        System.out.println("����·��"+file.getAbsoluteFile());
        //�����һ��·��
        System.out.println("��һ��·��"+file.getAbsoluteFile().getParent());
        //�ڵ�ǰ·���´���һ����ʱ�ļ�
        File tmpFile=File.createTempFile("aaa",".txt",file);
        //ָ����JVM�˳�ʱ��ɾ�����ļ�
        tmpFile.deleteOnExit();
        //��file����·���µ������ļ�������String[]��,Ȼ����������ǰ·�������ļ�
        String[] fileList=file.list();
        System.out.println("--------��ǰ·���������ļ���·������-------");
        for (String fileName:fileList){
            System.out.println(fileName);
        }
        //��ӡϵͳ��·��
        File[] roots=File.listRoots();
        System.out.println("--------------ϵͳ���и�·������-----------");
        for (File root:roots){
            System.out.println(root);
        }
    }
}
