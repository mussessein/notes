package JDBC;
import java.sql.*;
/*   JDBC事务：（只能存在一个Connection对象）
* 1.开启事务 start transaction
* 2.进行sql操作
* 3.commit提交/rollback回滚
*
* */

public class demo_Transaction {

    public static final String Driver_Name="com.mysql.cj.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";
    Connection connection =null;

    //整个事务操作只能有一个Connection对象，
    public void transfer(String from,String to,double money){

        try {
            //对事务的操作必须使用Connection对象
            connection = DriverManager.getConnection(URL, User_Name, Password);
            //开启事务
            connection.setAutoCommit(false);
            demo_Transaction dt=new demo_Transaction();
            //设置转账方from，减去金额
            dt.updateBalance(connection,from,-money);
            //设置收账方to，加上金额
            dt.updateBalance(connection,to,money);
            //提交事务
            connection.commit();
            connection.close();

        }catch (Exception e){
            try{      //如果抛出异常，自动回滚事务
                connection.rollback();
                connection.close();
            }catch (SQLException e1){e1.printStackTrace();}
        }
    }
    //修改某个账户的金额
    //整个事务操作只能有一个Connection对象，通过传入的参数，获得链接
    public void updateBalance(Connection connection,String name,double money){

        try {
            //设置某人的账户加上金额
            String sql="update stu3 set money=money+? where name=?";
            PreparedStatement prsmt=connection.prepareStatement(sql);
            prsmt.setDouble(1,money);
            prsmt.setString(2,name);
            prsmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        //zs给ls转账500
        new demo_Transaction().transfer("ls","zs",1000);
    }
}












