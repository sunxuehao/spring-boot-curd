package org.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        //这里相当于redis对String类型的set操作
        redisTemplate.opsForValue().set("test", "helloworld");
        //这里相当于redis对String类型的get操作
        String test = (String) redisTemplate.opsForValue().get("test");
        System.out.println(test);
    }

//    @Test
//    public static void main(String[] args) {
//        //连接本地的 Redis 服务
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("连接成功");
//        //查看服务是否运行
//        System.out.println("服务正在运行: " + jedis.ping());
//        jedis.set("runoobkey", "www.runoob.com");
//        // 获取存储的数据并输出
//        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
//        jedis.lpush("site-list", "Runoob");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
//        // 获取存储的数据并输出
//        List<String> list = jedis.lrange("site-list", 0, 2);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("列表项为: " + list.get(i));
//        }
//        // 获取数据并输出
//        Set<String> keys = jedis.keys("*");
//        Iterator<String> it = keys.iterator();
//        while (it.hasNext()) {
//            String key = it.next();
//            System.out.println(key);
//        }
//    }
}