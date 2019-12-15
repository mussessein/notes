package Utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * DML：
 * 1. 插入，修改数据 put
 * 2. 查询数据
 *      get
 *      scan
 * 3. 删除数据 drop
 */
public class DMLUtils {
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
    /**
     * 插入数据
     * @param tableName：表名
     * @param rowKey
     * @param cf：列族
     * @param cn：列
     * @param value：数据
     */
    public static void putData(String tableName,String rowKey,String cf,String cn,String value) throws IOException {

        // 表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 通过rowKey创建put对象,添加列族,列,value
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn),Bytes.toBytes(value));
        table.put(put);
        table.close();
    }

    /**
     * get方法
     * @param tableName
     * @param rowKey
     * @throws IOException
     */
    public static void get(String tableName,String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        Result result = table.get(get);
        // 解析result,打印
        Cell[] cells = result.rawCells();
        for (Cell cell :cells){
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String column = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println("CF:"+family+"\t"+"CN:"+column+"\t"+"Value:"+value);
        }
        table.close();
    }

    /**
     * get
     * @param tableName
     * @param rowKey
     * @param cf
     * @throws IOException
     */
    public static void get(String tableName,String rowKey,String cf) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 获取get对象,并指定列族
        Get get = new Get(Bytes.toBytes(rowKey)).addFamily(Bytes.toBytes(cf));
        Result result = table.get(get);
        // 解析result,打印
        Cell[] cells = result.rawCells();
        for (Cell cell :cells){
            String family = Bytes.toString(CellUtil.cloneFamily(cell));
            String column = Bytes.toString(CellUtil.cloneQualifier(cell));
            String value = Bytes.toString(CellUtil.cloneValue(cell));
            System.out.println("CF:"+family+"\t"+"CN:"+column+"\t"+"Value:"+value);
        }
        table.close();
    }

    /**
     * 扫描全表
     * @param tableName
     */
    public static void scan(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        for (Result result:results){
            Cell[] cells = result.rawCells();
            for (Cell cell :cells){
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println("rowKey:"+rowKey+"\t"+"CF:"+family+"\t"+"CN:"+column+"\t"+"Value:"+value);
            }
        }
        table.close();
    }

    /**
     * scan指定RowKey
     * @param tableName
     * @param start:rowKey开始
     * @param end:结束
     * @throws IOException
     */
    public static void scan(String tableName,String start,String end) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan(Bytes.toBytes(start),Bytes.toBytes(end));
        ResultScanner results = table.getScanner(scan);
        for (Result result:results){
            Cell[] cells = result.rawCells();
            for (Cell cell :cells){
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                String family = Bytes.toString(CellUtil.cloneFamily(cell));
                String column = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                System.out.println("rowKey:"+rowKey+"\t"+"CF:"+family+"\t"+"CN:"+column+"\t"+"Value:"+value);
            }
        }
        table.close();
    }

    /**
     * 删除一个数据
     * @param tableName
     * @param rowKey
     * @param cf
     * @param cn
     */
    public static void delete(String tableName,String rowKey,String cf,String cn) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 指定列族,列,还可以添加时间戳
        delete.addColumns(Bytes.toBytes(cf),Bytes.toBytes(cn));
        table.delete(delete);
        table.close();
    }

    /**
     * 删除一个RowKey
     * @param tableName
     * @param rowKey
     */
    public static void deleteAll(String tableName,String rowKey) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
        table.close();
    }
    public static void main(String[] args) {
        // 插入数据
        try {
//            putData("student", "1001", "info1", "name", "gousheng");
//            putData("student", "1001", "info1", "age", "17");
//            putData("student", "1001", "info1", "addr", "nanjing");
//            putData("student", "1001", "info2", "class", "3-2");
            //get("student","1001","info");
            delete("student","1001","info1","addr");
            scan("student");

            //deleteAll("student","1001");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
