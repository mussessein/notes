package JDBC;

import java.sql.*;
//mysql打开批量处理的指令，写在URL中
//rewriteBatchedStatements=true
public class demo_Pi {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?rewriteBatchedStatements=true&serverTimezone=UTC";
    public static final String User_Name="root";
    public static final String Password="123456";

    public static void main(String[] args) throws Exception{

        Class.forName(Driver_Name);
        Connection connection = DriverManager.getConnection(URL, User_Name, Password);
        String sql="insert into stu2 value (?,?,?)";
        PreparedStatement prst = connection.prepareStatement(sql);
        for (int i=0;i<1000;i++){
            //在sql语句的指定参数位置插入数据
            prst.setInt(1,i);
            prst.setString(2,"校长"+i);
            prst.setInt(3,40);
            //
            prst.addBatch();
        }
        //执行批处理
        long start=System.currentTimeMillis();
        prst.executeBatch();
        long end=System.currentTimeMillis();
        //不打开mysql的批量处理执行了4530ms
        //打开mysql批量处理
        System.out.println(end-start);
    }
}
