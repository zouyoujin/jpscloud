package com.jspcloud.admin.web.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @ClassName: MyUsernamePasswordAuthenticationFilter
 * @Description: 自定义用户名密码过滤器扩展,支持验证码验证及Ajax请求方式登录
 * @author: Kitty
 * @date: 2018年8月4日 下午11:05:18
 *
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	// 是否开启验证码功能
	private boolean isOpenValidateCode = false;

	public static final String VALIDATE_CODE = "validateCode";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (isOpenValidateCode) {
			checkValidateCode(request);
		}
		// 根据请求类型是否是json数据提交
		if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())
				|| MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())) {

			// use jackson to deserialize json
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken authRequest = null;
			try (InputStream is = request.getInputStream()) {
				Map<String, Object> params = mapper.readValue(is, LinkedHashMap.class);
				authRequest = new UsernamePasswordAuthenticationToken(String.valueOf(params.get("userName")).trim(),
						String.valueOf(params.get("password")));
			} catch (IOException e) {
				e.printStackTrace();
				authRequest = new UsernamePasswordAuthenticationToken("", "");
			}
			setDetails(request, authRequest);
			return this.getAuthenticationManager().authenticate(authRequest);
		} else {
			return super.attemptAuthentication(request, response);
		}
	}

	protected void checkValidateCode(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String sessionValidateCode = obtainSessionValidateCode(session);
		sessionValidateCode = "1234";// 做个假的验证码；
		// 让上一次的验证码失效
		session.setAttribute(VALIDATE_CODE, null);
		String validateCodeParameter = obtainValidateCodeParameter(request);
		if (StringUtils.isEmpty(validateCodeParameter)
				|| !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
			throw new AuthenticationServiceException("验证码错误！");
		}
	}

	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString();
	}

}