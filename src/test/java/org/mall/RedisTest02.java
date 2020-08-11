package org.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mall.common.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest02 {
    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void getRedis() {
        redisUtils.set("r", "sun");
        System.out.println(redisUtils.get("r"));
    }


    @Test
    public void contextLoads() {
        //这里相当于redis对String类型的set操作
        redisTemplate.opsForValue().set("test", "孙学昊");
        //这里相当于redis对String类型的get操作
        String test = (String) redisTemplate.opsForValue().get("test");
        System.out.println(test);

        Map newMap = new HashMap();
        newMap.put("key1", "值1");
        newMap.put("key2", "值2");
        newMap.put("key3", "值3");
        newMap.put("key4", "值4");
        redisTemplate.opsForHash().putAll("hashkey1", newMap);
        System.out.println(redisTemplate.opsForHash().get("hashkey1", "key3"));

        System.out.println("下面是对list的操作");

    }

    @Test
    public void runPipelined() {
//        下面这个方法是对redis流水线进行测试
        Long start = System.currentTimeMillis();

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {

                for (int i = 1; i <= 100000; i++) {
                    operations.opsForValue().set((K) String.format("key%d", i), (V) String.format("value%d", i));
                    String value = (String) operations.opsForValue().get(String.format("key%d", i));

                    if (i == 100000) {
                        System.out.println("第100000个key：：：" + value);
                    }
                }
                return null;
            }
        });
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "s");

    }

    @Test
    public void runNoPipelined() {
//        下面这个方法是对redis流水线进行测试
        Long start = System.currentTimeMillis();

        for (int i = 1; i <= 100000; i++) {
            redisTemplate.opsForValue().set(String.format("key%d", i), String.format("value%d", i));
            String value = (String) redisTemplate.opsForValue().get(String.format("key%d", i));

            if (i == 100000) {
                System.out.println("第100000个key：：：" + value);
            }
        }
        Long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) + "s");

    }
}
