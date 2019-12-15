package Redis.Util;

import Redis.entity.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

public class JedisPoolUtil {
    /**
     *  配置连接池
     */
    private static JedisPool jedisPool;
    static{
        // 1. 连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(5);  // 最大连接数
        poolConfig.setMaxIdle(1);   // 最大空闲数：空闲的时候，保留一个连接
        // 2. 创建连接池
        String host="master";
        int port=6379;
        JedisPool jedisPool = new JedisPool(poolConfig, host, port);
    }
    public static Jedis getJedis(){
        // 3. 创建一个Jedis链接
        Jedis jedis = jedisPool.getResource();
        jedis.auth("123456");
        jedis.select(1);
        return jedis;
    }
    public static void close(Jedis jedis){
        jedis.close();
    }
    /**
     * Jedis对Hash类型操作：
     * 使用hash存储结构，存储一个对象：
     * 1. redis存在此对象，直接get
     * 2. redis不存在此对象，从数据库查询到redis
     */
    @Test
    public void hashTest(){
        //Jedis jedis = JedisPoolUtil.getJedis();
        Jedis jedis = new Jedis("master", 6379);
        jedis.select(1);
        int id = 10;
        String key = "user:"+ id;
        if (jedis.exists(key)){
            // 从redis查询
            Map<String, String> map = jedis.hgetAll(key);
            User u = new User();
            u.setId(Integer.parseInt(map.get("id")));
            u.setName(map.get("name"));
            u.setAge(Integer.parseInt(map.get("age")));
            u.setUsername(map.get("username"));
            u.setPassword(map.get("password"));
            System.out.println("Redis查询结果：");
            System.out.println(u.toString());
        }else{
            // 从数据库查询
            User u =new User();
            u.setId(id);
            u.setName("coco");
            u.setAge(17);
            u.setUsername("xu");
            u.setPassword("passwd");
            Map<String,String> hashmap =new HashMap();
            hashmap.put("id",u.getId()+"");
            hashmap.put("name",u.getName());
            hashmap.put("age",u.getAge()+"");
            hashmap.put("username",u.getUsername());
            hashmap.put("password",u.getPassword());
            // 数据库查询结果存入redis
            jedis.hmset(key,hashmap);
            System.out.println("数据库查询结果：");
            System.out.println(u.toString());
        }
        JedisPoolUtil.close(jedis);
    }

}
