package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.util.Arrays;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusDemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        // 根据id查询
        User user = userMapper.selectById(1);

        // 查询总数
        int userCount = userMapper.selectCount(null);

        // 插入
        userMapper.insert(user);

        // 分页条件查询构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .eq("name", user.getName())
                .ge("age", 11);
        Page<User> page = new Page<>();
        page = userMapper.selectPage(page, queryWrapper);
        /*
         SELECT id,name,password,age,email,create_time
         FROM user
         WHERE (name = '哈哈山' AND age >= 11)
         LIMIT 0,10;*/

        // 查询单个
        userMapper.selectOne(new QueryWrapper<User>().eq("id", 1));
        /*
        SELECT id,name,password,age,email,create_time
        FROM user
        WHERE (id = 1);*/


        // 更新按照id
        User updateUser = new User();
        updateUser.setId(1);
        updateUser.setAge(100);
        userMapper.updateById(updateUser);

        // 按条件更新
        User updateUser2 = new User();
        updateUser2.setAge(100);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>().eq("age", 11);
        userMapper.update(updateUser2, updateWrapper);
        // UPDATE user SET age=100 WHERE (age = 11);

        // 按照id批量删除
        userMapper.deleteBatchIds(Arrays.asList(9, 10));
       /*
        DELETE
        FROM user
        WHERE id IN ( 9 , 10 );*/

    }


}
