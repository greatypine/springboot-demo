package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonLang3Tests {

    @Test
    public void test1(){
        List<String> listStr = new ArrayList<>();
        listStr.add("a");
        listStr.add("b");
        listStr.add("c");
        listStr.add("e");
        listStr.add("f");
        String join = StringUtils.join(listStr, ",",2,5);
        System.out.println(join);
    }



}
