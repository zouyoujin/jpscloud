package com.jspcloud.admin.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.jpscloud.common.utils.JsonUtils;
import com.jpscloud.common.utils.ResponseService;

public class CustomAccessDeineHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("text/javascript;charset=utf-8");
		response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");//对于ajax请求不重定向  而是返回错误代码
		ResponseService<String> res = new ResponseService<>();
		res.setStatus(403);
		res.setData("没有访问权限!");
		response.getWriter().print(JsonUtils.toJson(res));
	}

}
