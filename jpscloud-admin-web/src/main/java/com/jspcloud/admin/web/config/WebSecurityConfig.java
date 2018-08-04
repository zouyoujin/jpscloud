package com.jspcloud.admin.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import com.jspcloud.admin.web.service.UserDetailsServiceImpl;

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

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private LogoutHandler logoutHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices tbrms = new TokenBasedRememberMeServices("testallKey", userDetailService);
		// 设置cookie过期时间为2天
		tbrms.setTokenValiditySeconds(60 * 60 * 24 * 2);
		// 设置checkbox的参数名为rememberMe（默认为remember-me），注意如果是ajax请求，参数名不是checkbox的name而是在ajax的data里
		tbrms.setParameter("rememberMe");
		tbrms.setAlwaysRemember(false);
		return tbrms;
	}

	@Bean
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		RememberMeAuthenticationProvider rmap = new RememberMeAuthenticationProvider("testallKey");
		return rmap;
	}

	@Bean
	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() throws Exception {
		RememberMeAuthenticationFilter myFilter = new RememberMeAuthenticationFilter(authenticationManagerBean(),
				tokenBasedRememberMeServices());
		return myFilter;
	}

	@Bean
	public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
		MyUsernamePasswordAuthenticationFilter myFilter = new MyUsernamePasswordAuthenticationFilter();
		myFilter.setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
		myFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
		myFilter.setRememberMeServices(tokenBasedRememberMeServices());
		myFilter.setFilterProcessesUrl("/user/login");
		// 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
		myFilter.setAuthenticationManager(authenticationManagerBean());
		return myFilter;
	}

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

		// http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();, "/api/**"

		http.authorizeRequests().antMatchers("/user/loginpage", "/user/login", "/getCode", "/user/logout" )
				.permitAll()
				// 其他地址的访问均需验证权限（需要登录）
				.anyRequest().authenticated().and()
				// 指定登录页面的请求路径
				.formLogin().loginPage("/user/loginpage")
				// 登陆处理路径
				.loginProcessingUrl("/user/login").permitAll().and()
				// 退出请求的默认路径为logout，下面改为signout，
				// 成功退出登录后的url可以用logoutSuccessUrl设置
				// 添加自定义异常入口，处理accessdeine异常
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeineHandler()).and().logout()
				.deleteCookies("remember-me").clearAuthentication(true).logoutUrl("/user/logout")
				.logoutSuccessHandler(logoutSuccessHandler).addLogoutHandler(logoutHandler).and()
				// 开启rememberMe，设置一个私钥专供testall项目使用，注意与下面TokenBasedRememberMeServices的key保持一致
				// .rememberMe().key("testallKey").and()
				// 关闭csrf
				.csrf().disable();

		// 用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
		http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(new MyLoginUrlAuthenticationEntryPoint("/user/loginpage")).and();
		http.addFilterAt(rememberMeAuthenticationFilter(), RememberMeAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder()).and()
				.authenticationProvider(rememberMeAuthenticationProvider());
	}

}
