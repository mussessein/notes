package JDBC;

import java.sql.*;
//mysql�����������ָ�д��URL��
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
            //��sql����ָ������λ�ò�������
            prst.setInt(1,i);
            prst.setString(2,"У��"+i);
            prst.setInt(3,40);
            //
            prst.addBatch();
        }
        //ִ��������
        long start=System.currentTimeMillis();
        prst.executeBatch();
        long end=System.currentTimeMillis();
        //����mysql����������ִ����4530ms
        //��mysql��������
        System.out.println(end-start);
    }
}
