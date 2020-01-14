package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * DDL:
 * 1. 判断表是否存在
 * 2. 创建表
 * 3. 创建命名空间
 * 4. 删除表
 */

public class DDLUtils {
    private  static Connection connection;
    private static Admin admin;
    private static final String ZK_QUORUM = "hbase.zookeeper.quorum";
    private static final String ZK_POS = "master,slave1,slave2";

    static {
        Configuration conf = HBaseConfiguration.create();
        //创建连接池
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 关闭连接
    public static void close(){
        if (admin!=null){
            try {
                admin.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        if (connection!=null){
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 1. 判断表是否存在
    public static boolean isTableExist(String table) throws IOException {
        TableName tableName = TableName.valueOf(table);
        boolean exists = admin.tableExists(tableName);
        return exists;
    }

    /**
     * 2. 创建表
     * @param table：表名,namespace:table
     * @param cfs：列族名
     */
    public static void create(String table,String ...cfs) throws IOException {
        // 1.如果列族为空，则return
        if (cfs.length==0){
            System.out.println("列族为空");
            return;
        }
        if (isTableExist(table)){
            System.out.println("表已存在");
            return;
        }
        TableName tableName = TableName.valueOf(table);
        HTableDescriptor descriptor = new HTableDescriptor(tableName);
        for(String cf:cfs){
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(cf);
            descriptor.addFamily(columnDescriptor);
        }
        admin.createTable(descriptor);
        //close();
    }

    /**
     * 删除表
     * @param table：表名
     * @throws IOException
     */
    public static void  dropTable(String table) throws IOException {
        if (!isTableExist(table)){
            System.out.println(table + "表不存在");
            return;
        }
        TableName tableName = TableName.valueOf(table);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    /**
     * 创建命名空间
     * @param nameSpace：命名空间名
     */
    public static void createNameSpace(String nameSpace){
        // 命名空间描述器
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(nameSpace).build();
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        create("student","info1","info2");
//        boolean exist = DDLUtils.isTableExist("student1");
//        System.out.println(exist);
//        dropTable("student");
        //createNameSpace("bigdata");

        close();
    }
}
