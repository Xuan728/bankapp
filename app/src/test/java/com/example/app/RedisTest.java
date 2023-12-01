package com.example.app;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){

        ValueOperations<String, String>  operations = stringRedisTemplate.opsForValue();
        operations.set("username","zhangsan");
        operations.set("id","1",15, TimeUnit.SECONDS);
    }

    @Test
    public void testGet(){
        ValueOperations<String, String>  operations = stringRedisTemplate.opsForValue();
        String username = operations.get("username");
        System.out.println(username);
    }
}
