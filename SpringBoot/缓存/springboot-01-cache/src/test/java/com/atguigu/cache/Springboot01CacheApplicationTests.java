package com.atguigu.cache;

import com.atguigu.cache.bean.Employee;
import com.atguigu.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {
	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的

	@Autowired
	RedisTemplate redisTemplate;  //k-v都是对象的

	@Autowired
	RedisTemplate<Object, Employee> empRedisTemplate;


	/**
	 * Redis常见的五大数据类型
	 *  String（字符串）、List（列表）、Set（集合）、Hash（散列）、ZSet（有序集合）
	 *  stringRedisTemplate.opsForValue()[String（字符串）]
	 *  stringRedisTemplate.opsForList()[List（列表）]
	 *  stringRedisTemplate.opsForSet()[Set（集合）]
	 *  stringRedisTemplate.opsForHash()[Hash（散列）]
	 *  stringRedisTemplate.opsForZSet()[ZSet（有序集合）]
	 */
	@Test
	public void test01(){
		// String类型添加数据
	    stringRedisTemplate.opsForValue().append("msg","hello");
		String msg = stringRedisTemplate.opsForValue().get("msg");
		System.out.println(msg);

		stringRedisTemplate.opsForList().leftPush("mylist","1");
		stringRedisTemplate.opsForList().leftPush("mylist","2");
	}

	/**
	 * Redis测试
	 */
	@Test
	public void test02(){
		Employee empById = employeeMapper.getEmpById(1);
		/**
		 * redisTemplate默认使用jdk序列化机制
		 */
		redisTemplate.opsForValue().set("emp-01",empById);
	}
	@Test
	public void test03(){
		/**
		 * 使用json的方式序列化对象
		 * 注入此对象的RedisTemplate方法
		 */
		Employee empById = employeeMapper.getEmpById(1);
		empRedisTemplate.opsForValue().set("emp-01",empById);
		redisTemplate.opsForValue().set("test","test");
	}

	@Test
	public void contextLoads() {

		Employee empById = employeeMapper.getEmpById(1);
		System.out.println(empById);

	}

}
