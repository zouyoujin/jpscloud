package com.jpscloud.admin.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @ClassName: WebStartApplication
 * @Description:后台功能启动类
 * @author: Kitty
 * @date: 2018年7月29日 上午12:59:47
 *
 */
@SpringBootApplication
@MapperScan(basePackages = "com.jpscloud.admin.web.mapper")
public class AdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminWebApplication.class, args);
	}
}