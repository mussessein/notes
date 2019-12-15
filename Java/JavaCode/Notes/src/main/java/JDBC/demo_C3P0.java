package JDBC;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.*;
/*
需要c3p0-0.9.1.2.jar包
C3P0数据源性能更好，
1.不仅可以自动清理不再使用的Connection
2.还可以自动清理Statement和ResultSet
 */
public class demo_C3P0 {

    public void fun() throws PropertyVetoException, SQLException {
         //创建连接池实例，配置四大参数
        ComboPooledDataSource cpds=new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/student?serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("123456");

        cpds.setMaxPoolSize(40);   //连接池最大链接数
        cpds.setMinPoolSize(2);    //设置最小链接数
        cpds.setInitialPoolSize(10);  //设置连接池初始链接数
        cpds.setMaxStatements(180);  //设置连接池的缓存Statements的最大数

        //获取链接对象Connetction
        Connection connection=cpds.getConnection();


    }
}

