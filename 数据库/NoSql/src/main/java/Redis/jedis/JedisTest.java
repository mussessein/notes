package Redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
jedis 的字符串类型的操作
 */
public class JedisTest {
    public static void main(String[] args) {
        /*
        1.创建Jedis对象，与Redis进行远程链接需要：终端ip和Redis的端口
        2. jedis.auth(); 输入链接的密码
        3. 选择数据库,操作数据库
        4.关闭连接
         */
        Jedis jedis = new Jedis("master", 6379);
        try {
            jedis.auth("123456");
            jedis.select(0);
            System.out.println("成功连接Redis :)");
            /*
            1.jedis字符串操作
             */
            System.out.println("================字符串操作=================");
            jedis.set("name", "gdx1");
            String name = jedis.get("name");
            System.out.println(name);
            //mset方法：批量操作 {key,value,key,value.....}
            jedis.mset("age", "17", "date", "1996-12-08");
            List<String> gdx = jedis.mget(new String[]{"name", "age", "date"});
            System.out.println(gdx);
            //加一命令,必须用long
            long age = jedis.incr("age");
            System.out.println("incr后："+age);

            /*
            2.jedis的Hash操作
             */
            System.out.println("================Hash操作=================");
            jedis.hset("student:1","name","gdx2");
            String name2 =jedis.hget("student:1","name");
            System.out.println("student:1--"+name2);
            //通过Map批量加入数据
            Map<String, String> studentMap = new HashMap<String,String>();
            studentMap.put("name","小李");//在Redis中每个中文使用三个字节（UTf-8）
            studentMap.put("age","18");
            studentMap.put("date","1999");
            jedis.hmset("student:2",studentMap);
            Map<String,String> smp=jedis.hgetAll("student:2");
            System.out.println("student:2--"+smp);

            /*
            3.jedis的List操作：
            注意，每次运行一次，都会在插入同样的数据，所以每次先删除
             */
            System.out.println("================List操作=================");
            jedis.del("student:3");
            //先右侧插入，再左侧
            jedis.rpush("student:3", new String[]{"li", "zhang", "zhao"});
            jedis.lpush("student:3", new String[]{"sun", "shi", "zhu"});
            List<String> slist=jedis.lrange("student:3",0,-1);
            System.out.println("student:3--"+slist);
            //左侧弹出一个
            jedis.lpop("student:3");
            jedis.rpop("student:3");
            slist=jedis.lrange("student:3",0,-1);
            System.out.println("student:3--"+slist);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }
}
