package com.jspcloud.admin.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpscloud.common.utils.JsonUtils;
import com.jpscloud.common.utils.ResponseService;

/**
 * 
 * @ClassName: WebSecurityHandlerConfig
 * @Description:WebSecurity相关回调配置处理
 * @author: Kitty
 * @date: 2018年7月22日 上午2:53:18
 *
 */
@Component
@Configuration
public class WebSecurityHandlerConfig {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Resource
	private ObjectMapper objectMapper; // springmvc启动时自动装配json处理类

//	/**
//	 * 登陆成功，返回Token 装配此bean不支持授权码模式
//	 * 
//	 * @return
//	 */
//	@Bean
//	public AuthenticationSuccessHandler loginSuccessHandler() {
//		return new SavedRequestAwareAuthenticationSuccessHandler() {
//
//			private RequestCache requestCache = new HttpSessionRequestCache();
//
//			@Override
//			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//					Authentication authentication) throws IOException, ServletException {
//				super.onAuthenticationSuccess(request, response, authentication);
//				log.info("..........onAuthenticationSuccess.........." + requestCache);
//				// 根据请求类型是否是json数据提交
//				if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)
//						|| request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
////					String principal = authentication.getPrincipal().toString();
////					ResponseService<String> res = new ResponseService<String>();
////					res.setData(principal);
//					response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//					PrintWriter out = response.getWriter();
//					Map<String,String> result = new HashMap<>();
//					result.put("status", "ok");
//					result.put("currentAuthority", "admin");
//					out.print(JsonUtils.toJson(result));
//					out.flush();
//					//out.close();
//				} else {
//					super.onAuthenticationSuccess(request, response, authentication);
//				}
//			}
//		};
//	}

	/**
	 * 登陆失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				String msg = null;
				if (exception instanceof BadCredentialsException) {
					msg = "用户名或密码错误";
				} else {
					msg = exception.getMessage();
				}
				Map<String, String> rsp = new HashMap<>();
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				rsp.put("resp_code", HttpStatus.UNAUTHORIZED.value() + "");
				rsp.put("rsp_msg", msg);
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().write(objectMapper.writeValueAsString(rsp));
				response.getWriter().flush();
				response.getWriter().close();
			}
		};

	}

	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				log.info("====================LogoutSuccessHandler==========================");
				// 根据请求类型是否是json数据提交
				if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())
						|| MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {
					response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
					PrintWriter out = response.getWriter();
					ResponseService<Boolean> res = new ResponseService<>();
					res.setData(true);
					out.print(JsonUtils.toJson(res));
					out.flush();
					out.close();
				}else{
					if (StringUtils.isNotBlank(request.getHeader("referer"))) {
						response.sendRedirect(request.getHeader("referer"));
					}
				}
			}
		};
	}

	@Bean
	public LogoutHandler logoutHandler() {
		return new LogoutHandler() {

			@Override
			public void logout(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) {
//				try {
//					response.sendRedirect("/login");
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
			}
		};
	}
}
