package JDBC;
import java.sql.*;
/*
*  ����һ������ķ�������ִ��
* */
public class Demo_Create {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";


    public void createTable(String sql) throws Exception {
        Class.forName(Driver_Name);
        try (
                Connection connection=DriverManager.getConnection(URL,User_Name,Password);
                Statement stmt=connection.createStatement()
                )
        {
            stmt.executeUpdate(sql);
        }
    }


    public static void main(String[] args) throws Exception{
        Demo_Create demo=new Demo_Create();
        demo.createTable("create table stu3(num int,name varchar(200))");
        System.out.println("--------����ɹ�--------");
    }

}
