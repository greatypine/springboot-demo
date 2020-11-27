package com.example.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlTest {
    public static void main(String[] args){
        //mysql 5.x 驱动
		//String driver = "com.mysql.jdbc.Driver";

        //mysql 8.0 驱动
        String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=convertToNull&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pwd = "123456";
		Connection conn = null;
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (Exception ex) {
			System.out.println("Error : " + ex.toString());
		}
		if (conn!=null){
            System.out.println("Mysql Connection Succeed");
        } else {
            System.out.println("Mysql Connection Failed");
        }
	}
	

}
