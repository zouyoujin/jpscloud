package com.jpscloud.admin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: WebErrorController
 * @Description:全局页面错误处理
 * @author: Kitty
 * @date: 2018年8月5日 上午11:35:49
 *
 */
@Controller
public class WebErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		// 获取statusCode:401,404,500
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 401) {
			return "/401";
		} else if (statusCode == 404) {
			// 配合react antd pro单页面路由功能，404需要处理跳转到index页面
			return "/index";
		} else if (statusCode == 403) {
			return "/403";
		} else {
			return "/500";
		}
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
