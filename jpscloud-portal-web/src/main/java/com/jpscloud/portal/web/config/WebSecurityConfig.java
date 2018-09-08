package com.jpscloud.portal.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//import com.kitty.springcloud.oauth.server.security.UserDetailServiceImpl;

/**
 * 
 * @author kitty
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 静态资源过滤忽略
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security",
				"/swagger-ui.html", "/webjars/**", "/doc.html", "/login.html", "/static/**");
		web.ignoring().antMatchers("/health");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();,
		// "/api/**"

		http.authorizeRequests().antMatchers("/user/login", "/user/dologin", "/getcode", "/user/logout").permitAll()
				.antMatchers("/**").permitAll()
				// 其他地址的访问均需验证权限（需要登录）
				.anyRequest().authenticated().and()
				// 指定登录页面的请求路径
				.formLogin().loginPage("/user/login")
				// 登陆处理路径
				.loginProcessingUrl("/user/dologin").permitAll().and()
				// 关闭csrf
				.csrf().disable();
	}

}
