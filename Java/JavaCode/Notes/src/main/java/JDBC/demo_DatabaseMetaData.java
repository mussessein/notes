package JDBC;

import javax.xml.transform.Result;
import java.sql.*;

public class demo_DatabaseMetaData {

    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    public void info() throws Exception {
        Class.forName(Driver_Name);
        try(
                Connection connection= DriverManager.getConnection(URL,User_Name,Password);
                ){
            //获取DatabaseMetaData对象
            DatabaseMetaData dm=connection.getMetaData();

            // 1.获取mysql支持的所有表类型
            ResultSet rs=dm.getTableTypes();
            System.out.println("---------获取mysql支持的所有表类型------------");
            printResultSet(rs);

            //获取当前数据库的全部表
            rs=dm.getTables(null,null,"%",new String[]{"Table"});
            System.out.println("---------获取当前数据库的所有表------------");
            printResultSet(rs);

            //获取表stu3的全部数据列
            rs=dm.getColumns(null,null,"stu3","%");
            System.out.println("---------获取当前数据库的stu3的数据列------------");
            printResultSet(rs);

        }
    }

    public void printResultSet(ResultSet rs) throws SQLException {

        ResultSetMetaData rsmd=rs.getMetaData();
        //打印ResultSet所有列标题
        for (int i=1;i<rsmd.getColumnCount();i++){
            System.out.print(rsmd.getColumnName(i)+"\t");
        }
        System.out.println("\n");

        //打印ResultSet的全部数据
        while(rs.next()){
            for (int i=1;i<rsmd.getColumnCount();i++){
                System.out.print(rs.getString(i)+"\t");
            }
            System.out.println("\n");
        }
        rs.close();
    }
    public static void main(String[] args) throws Exception {
        demo_DatabaseMetaData ddm=new demo_DatabaseMetaData();
        ddm.info();
    }
}
