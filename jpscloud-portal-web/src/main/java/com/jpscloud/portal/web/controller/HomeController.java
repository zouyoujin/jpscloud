package com.jpscloud.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpscloud.common.utils.VerifyCodeUtils;
import com.jpscloud.portal.web.constant.Constant;

/**
 * @ClassName: HomeController
 * @Description: 网站首页
 * @author: Kitty
 * @date: 2018年7月31日 上午6:19:19
 *
 */
@RestController
@RequestMapping(value = "")
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 网站首页入口
	 * 
	 * @return
	 */
	@GetMapping(value = "/")
	public ModelAndView indexPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/index");
		return modelAndView;
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/user/login")
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/login");
		return modelAndView;
	}
	
	/**
	 * 注册页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/user/reg")
	public ModelAndView reg(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/reg");
		return modelAndView;
	}
	
	/**
	 * 忘记密码页面
	 * 
	 * @return
	 */
	@GetMapping(value = "/user/forget")
	public ModelAndView forget(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/forget");
		return modelAndView;
	}

	/**
	 * 验证码生成功能
	 * 
	 * @param response
	 * @param request
	 */
	@GetMapping(value = "/getcode")
	public void getVerifyCode(HttpServletResponse response, HttpServletRequest request) {
		try {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpg");
			// 生成随机字串
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
			log.info("verifyCode:{}", verifyCode);
			// 存入会话session
			HttpSession session = request.getSession(true);
			session.setAttribute(Constant.Register.VERIFYCODE_KEY, verifyCode.toLowerCase());
			// 生成图片
			int w = 146, h = 33;
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
