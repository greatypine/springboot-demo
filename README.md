## springboot + mybatisPlus 入门实例 入门demo

### 使用mybatisPlus的优势

集成mybatisplus后，简单的CRUD就不用写了，如果没有特别的sql，就可以不用mapper的xml文件。

mybatisPlus官网： https://mp.baomidou.com/guide

### 项目搭建前提条件

1、IDEA，并且已安装lombok插件
2、mysql数据库，本实例采用mysql8.0版本，与mysql5.x版本有些不一样，如果使用mysql5.x，只需要修改相应的配置即可。

### 1. 导入相关依赖

如果项目中有mybatis依赖可以直接删除了，mybatisPlus里包含了mybatis的依赖

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- mybatisPlus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.3.2</version>
</dependency>

<!-- mysql 5.x驱动 -->
<!--<dependency>
         <groupId>mysql</groupId>
         <artifactId>mysql-connector-java</artifactId>
         <version>5.1.39</version>
</dependency>-->

<!-- mysql8.0 驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.20</version>
</dependency>

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.21</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### 2. yml 配置

```yaml
server:
  port: 8080
  servlet:
    context-path: /
  tomcat:
    uri-encoding: UTF-8

spring:
  datasource:
    # mysql5.x 配置，高版本需要加useSSL=false
    #url: jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false
    # mysql8.0 需要加&useSSL=false&serverTimezone=UTC
    url: jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
    username: root
    password: 123456
    # mysql8.0 驱动
    driver-class-name: com.mysql.cj.jdbc.Driver
    # mysql5.x 驱动
    #driver-class-name: com.mysql.jdbc.Driver
    debug: false
    #Druid#
    name: test
    type: com.alibaba.druid.pool.DruidDataSource
    filters: stat
    maxActive: 20
    initialSize: 1
    maxWait: 60000
    minIdle: 1
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: select 'x'
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    maxOpenPreparedStatements: 20

#-----------------------------------------------------
# pom.xml引用 mybatis-plus包，需要这样配置
#mybatis:
#  type-aliases-package: com.example.demo.model
#  mapper-locations: classpath:mybatis/mappers/*.xml
#  configuration:
#    map-underscore-to-camel-case: true
#-----------------------------------------------------

#-----------------------------------------------------
# pom.xml引用 mybatis-plus-boot-starter包，需要这样配置
mybatis-plus:
  type-aliases-package: com.example.demo.model
  mapper-locations: classpath:mybatis/mappers/*.xml
  configuration:
    map-underscore-to-camel-case: true
#-----------------------------------------------------
```

### 3. 代码

### User类

@TableId(type = IdType.AUTO) 表示我们使用自增主键

```java
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
```

### mapper类

继承BaseMapper就可以，会把一些crud的操作帮我们自动生成注入。

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.User;

public interface UserMapper extends BaseMapper<User> {
    public User findUserByName(String name);

}
```

### Controller类

```java
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据ID查找
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public String getUserById(@PathVariable String id){
        User user = userMapper.selectById(id);
        return "hello " + user.getName();
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
```

### UserMapper.xml 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.mapper.UserMapper">
    <resultMap id="userResultMap" type="com.example.demo.model.User" >
        <id column="id" property="id"  />
        <result column="name" property="name" />
        <result column="password" property="password"  />
        <result column="age" property="age"  />
        <result column="email" property="email"  />
        <result column="createTime" property="createTime"  />
    </resultMap>

    <select id="findUserByName" resultMap="userResultMap">
        SELECT
        id,
        name,
        password,
        age,
        email,
        createTime
        FROM user_demo
        where name = #{name}
    </select>

</mapper>
```

### 实例运行效果

浏览器输入：http://localhost:8080/user/name/aaa

![实例运行效果](https://tva1.sinaimg.cn/large/0080xEK2ly1ghsig27irjj30f3094aa1.jpg)

### 4.  写在最后

本实例源代码：https://gitee.com/jelly_oy/springboot-mybatisPlus-demo

本实例采用springboot2.3.3 + mybatis-plus3.3.2 + mysql8.0 进行搭建，如果使用mysql5.x，只需要修改相关配置即可。

如果本项目对你有帮助，欢迎留言评论，欢迎git clone源代码，  

也欢迎对本项目进行捐助，你的鼓励将是我开源创造的动力！  


![输入图片说明](https://images.gitee.com/uploads/images/2020/0817/120247_e39e4fe7_367913.png "a6x0079437ysmajwki57y16.png")
