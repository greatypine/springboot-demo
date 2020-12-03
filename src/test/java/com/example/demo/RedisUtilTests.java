package com.example.demo;

import com.example.demo.common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testStr(){
        RedisUtil.StringOps.set("company","快乐沃克");
        String company = RedisUtil.StringOps.get("company");
        System.out.println(company);

        List<String> numList = new ArrayList<>();
        numList.add("1");
        numList.add("2");
        numList.add("3");

        ValueOperations<String, Object> redisString  = redisTemplate.opsForValue();
        redisString.set("numList",numList);

        RedisUtil.KeyOps.delete("set");

    }

    @Test
    public void testMap(){
        HashMap hm = new HashMap();
        hm.put("name","zhangsan");
        hm.put("age","11");

        RedisUtil.HashOps.hPutAll("hm",hm);

        Set<String> myzset = new HashSet<>();
        myzset.add("1");
        RedisUtil.ZSetOps.zAdd("myzset",myzset.toString(),12);

    }

    @Test
    public void testSet(){


    }
}
