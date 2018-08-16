package com.jpscloud.portal.web;

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
@MapperScan(basePackages = "com.jpscloud.portal.web.mapper")
public class PortalWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortalWebApplication.class, args);
	}
}
