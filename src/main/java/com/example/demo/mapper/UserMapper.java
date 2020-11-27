package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.User;

public interface UserMapper extends BaseMapper<User> {
    public User findUserByName(String name);

}
