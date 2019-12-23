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
            //��ȡDatabaseMetaData����
            DatabaseMetaData dm=connection.getMetaData();

            // 1.��ȡmysql֧�ֵ����б�����
            ResultSet rs=dm.getTableTypes();
            System.out.println("---------��ȡmysql֧�ֵ����б�����------------");
            printResultSet(rs);

            //��ȡ��ǰ���ݿ��ȫ����
            rs=dm.getTables(null,null,"%",new String[]{"Table"});
            System.out.println("---------��ȡ��ǰ���ݿ�����б�------------");
            printResultSet(rs);

            //��ȡ��stu3��ȫ��������
            rs=dm.getColumns(null,null,"stu3","%");
            System.out.println("---------��ȡ��ǰ���ݿ��stu3��������------------");
            printResultSet(rs);

        }
    }

    public void printResultSet(ResultSet rs) throws SQLException {

        ResultSetMetaData rsmd=rs.getMetaData();
        //��ӡResultSet�����б���
        for (int i=1;i<rsmd.getColumnCount();i++){
            System.out.print(rsmd.getColumnName(i)+"\t");
        }
        System.out.println("\n");

        //��ӡResultSet��ȫ������
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
