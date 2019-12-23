package JDBC;

import org.apache.commons.dbcp2.*;

import java.sql.*;

/*
两个jar包：commons-dbcp.jar/commons-pool.jar
    1.连接池必须实现：javax.sql.DataSource
    2.DBCP将Connection对象的close方法进行了增强，不是关闭链接，而是归还链接
 */
public class demo_DBCP {

    /*
    1.创建连接池对象
    2.配置四大参数
    3.配置池参数
    4.得到链接对象
     */
    public void fun() throws SQLException {

        //创建连接池，配置四大参数
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/student?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        dataSource.setMaxTotal(20);//设置最大活动链接数
        dataSource.setMinIdle(3);  //最小空闲连接
        dataSource.setMaxWaitMillis(1000);//最大等待连接时间

        //得到链接对象
        Connection connection=dataSource.getConnection();
        System.out.println(connection.getClass().getName());
        //归还链接
        connection.close();
    }
}
