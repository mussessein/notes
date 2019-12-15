package JDBC;
import java.sql.*;
/* 执行sql语句的三种方法：
*  1.execute()执行任何sql语句，返回boolean值，如果结果为ResultSet，返回true；
*  2.executeQuery()执行select语句，返回查询到的结果集；
*  3.executeUpdate()若执行DML语句，返回一个整数，表示操作sql被影响的记录条数；
*                   若执行DDL，返回0；
*  DML（操作表内数据，insert，updata，delete....）
*  DDL(操作数据库对象，create，alter，drop....)
* */
public class demo {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";
    public static void main(String[] args){
        /*
         * 1. 创建链接connection
         * 2. 创建statement对象
         * 3. sql语句
         * 4. 结果集
         * */
        Connection connection=null;
        String sql=null;
        ResultSet rs=null;

        try {
            //加载驱动（反射）
            Class.forName(Driver_Name);
            //使用DriverManager获取数据库链接，并返回给connection，表示java和数据库的链接
            connection=DriverManager.getConnection(URL,User_Name,Password);
            //使用connection来创建一个Statement对象
            Statement stmt=connection.createStatement();
            //sql语句
            sql="select * from stu3";
            //执行sql语句，
            rs=stmt.executeQuery(sql);
            //ResultSet是一个指针，每次next执行，向下移动一行
            while(rs.next()){
                System.out.println(rs.getString(1)+"\t"+
                rs.getString(2));
            }
            /*循环遍历ResultSet：
             * int count =rs.getMetaData().getColumnCount();
             * for(int i=0;i<=count;i++){
             * 	System.out.print(rs.getString(i)+"\t");
             * }
             * */
            rs.close();
            stmt.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(connection!=null)connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
