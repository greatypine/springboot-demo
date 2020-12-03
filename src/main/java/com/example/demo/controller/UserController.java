package com.example.demo.controller;

import com.example.demo.common.base.ResponseInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    @ApiOperation("根据id获取")
    public ResponseInfo getUserById(@PathVariable String id){
        User user = userMapper.selectById(id);
        return ResponseInfo.success("hello " + user.getName());
    }

    /**
     * 根据name查找
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public String getUserByName(@PathVariable String name){
        User user = userMapper.findUserByName(name);
        return "getUserByName " + user.getName();
    }

}
