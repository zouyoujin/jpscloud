package com.jpscloud.admin.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @ClassName: WebStartApplication
 * @Description:后台功能启动类
 * @author: Kitty
 * @date: 2018年7月29日 上午12:59:47
 *
 */
@SpringBootApplication
//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })  //将spring boot自带的DataSourceAutoConfiguration禁掉
//在默认情况下只能扫描与控制器在同一个包下以及其子包下的@Component注解，所以公共模块创建的一些bean也需要初始化
@ComponentScan(basePackages = { "com.jpscloud.common", "com.jpscloud.admin.web" })
public class AdminWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminWebApplication.class, args);
	}
}
