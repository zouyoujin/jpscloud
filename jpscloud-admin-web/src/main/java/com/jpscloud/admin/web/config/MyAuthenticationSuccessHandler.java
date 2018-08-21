package com.jpscloud.admin.web.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.jpscloud.common.utils.JsonUtils;
import com.jpscloud.common.vo.ResponseService;

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 根据请求类型是否是json数据提交
		if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)
				|| request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			PrintWriter out = response.getWriter();
			ResponseService<Map<String,String>> res = new ResponseService<>();
			Map<String,String> result = new HashMap<String,String>();
			result.put("userName", authentication.getName());
			result.put("currentAuthority", StringUtils.collectionToCommaDelimitedString(authentication.getAuthorities()));
			res.setData(result);
			out.print(JsonUtils.toJson(res));
			out.flush();
			out.close();
		} else {
			SavedRequest savedRequest = requestCache.getRequest(request, response);
			String targetUrl = savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, targetUrl);
		}
	}

}
