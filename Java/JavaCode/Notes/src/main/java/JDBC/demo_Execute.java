package JDBC;
import java.sql.*;
/*
 *  execute()；
 *  通过此方法，建立一个可以同时执行不同类型的sql语句的方法！！！！！
 * */
public class demo_Execute {
    public static final String Driver_Name="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/student?serverTimezone=UTC&useSSL=false";
    public static final String User_Name="root";
    public static final String Password="123456";

    public void executeSQL(String sql) throws Exception{

        Class.forName(Driver_Name);
        try(    //获取数据库连接
                Connection connection=DriverManager.getConnection(URL,User_Name,Password);
                Statement stmt=connection.createStatement()
                )
        {
            /*执行不清楚类型的sql语句，execute根据执行的sql语句不同，返回的类型不同，
            如果返回boolean值，则说明返回了一个ResultSet对象，即可以遍历输出；
            如果返回了0，则可以输出一个sql被操作的次数；
             */
            boolean hasResultSet=stmt.execute(sql);
            //若果执行了查询语句，则会返回一个结果集对象，存储查询到的结果
            if (hasResultSet) {
                try (
                        //获取结果集
                        ResultSet rs = stmt.getResultSet()
                ) {
                    int count = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        for (int i = 1; i <= count; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.print("\n");
                    }
                }
            }else{ //没有返回ResultSet,则执行输出，sql操作次数
                System.out.println("该sql语句影响的记录有"+stmt.getUpdateCount()+"条");
            }
        }
    }


    public static void main(String[] args) throws Exception{
        demo_Execute demo=new demo_Execute();
        System.out.println("------执行删除表的DDL语句------");
        demo.executeSQL("drop table if exists stu3");

        System.out.println("------执行建立表的DDL语句------");
        demo.executeSQL("create table stu3(num int,name varchar(200))");

        System.out.println("------执行插入数据的DML语句------");
        demo.executeSQL("insert into stu3 values(1,'zhao')");

        System.out.println("------执行查询数据的DDL语句------");
        demo.executeSQL("select * from stu3");
    }
}
