package com.jspcloud.admin.web.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.jpscloud.common.utils.JsonUtils;
import com.jpscloud.common.utils.ResponseService;

/**
 * 
 * @ClassName: MyLoginUrlAuthenticationEntryPoint
 * @Description:重写自定义登录跳转逻辑，ajax请求方式返回json，form表单方式页面跳转
 * @author: Kitty
 * @date: 2018年8月4日 下午11:59:58
 *
 */
public class MyLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	public MyLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// 根据请求类型是否是json数据提交
		if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())
				|| MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {

			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			PrintWriter out = response.getWriter();
			ResponseService<String> res = new ResponseService<>();
			res.setMessage(authException.getMessage());
			res.setData("用户还没有登录，请先登录系统！");
			out.print(JsonUtils.toJson(res));
			out.flush();
			out.close();
		} else {
			super.commence(request, response, authException);
		}
	}

}
