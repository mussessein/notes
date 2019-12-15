package Redis.jedis;

import com.alibaba.fastjson.JSON;
import Redis.entity.Goods;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
关闭保护模式——protected-mode no
设置所有ip主机都可以访问redis——bind 0.0.0.0

1.通过json序列化，将保存对象转化为String，存放到redis
2.从redis提取数据，进行操作
 */
public class JedisGoodsTest {

    public JedisGoodsTest() {

        Jedis jedis = new Jedis("master", 6379);
        try {
            List<Goods> goodsList = new ArrayList<Goods>();
            goodsList.add(new Goods(8814, "红富士苹果", "", (float) 3.15));
            goodsList.add(new Goods(8815, "香蕉", "", (float) 3.88));
            goodsList.add(new Goods(8816, "脐橙", "", (float) 7.88));
            goodsList.add(new Goods(8817, "草莓", "", (float) 5.00));

            jedis.auth("123456");
            jedis.select(1);
            /*
            循环添加数据
            1.每一个goods对象转化为json
            2.将goods+ID作为key，详细信息作为value
             */
            for (Goods goods : goodsList) {
                String json = JSON.toJSONString(goods);
                System.out.println(json);
                String key = "goods" + goods.getGoodsID();
                jedis.set(key, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

    public static void main(String[] args) {

        JedisGoodsTest jedisGoodsTest = new JedisGoodsTest();
        /*
        查询操作
        1.再次连接，并设置密码，数据库号
        2.设置要输入的key
        3.用redis语法判断key是否存在
        4.直接输出查询到的字符串
        5.通过JSON将json转化为对象，然后用getter拿到对象属性
         */
        System.out.print("请输入要查询的商品编号：");
        String goodsID = new Scanner(System.in).next();
        Jedis jedis = new Jedis("master", 6379);
        try {
            jedis.auth("123456");
            jedis.select(1);
            String key ="goods"+goodsID;
            //判断key是否存在
            if (jedis.exists(key)){
                String json =jedis.get(key);
                System.out.println(json);
                //将json转化为对象，然后用getter拿到对象属性
                Goods goods = JSON.parseObject(json, Goods.class);
                System.out.println(goods.getGoodsName());
                System.out.println(goods.getPrice()+"元/斤");

            }else{
                System.out.println("商品编号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

    }
}
