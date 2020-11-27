package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("user_demo")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("password")
    private String password;

    @TableField("age")
    private Integer age;

    @TableField("email")
    private String email;

    /**
     * 注意：此处如果不加 @TableField注解，mybatisPlus会默认将createTime映射成 create_time字段
     * 由于数据库字段为createTime，所以需要指定
     */
    @TableField("createTime")
    private Date createTime;

}
