package com.jspcloud.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpscloud.common.entity.Users;
import com.jpscloud.common.utils.VerifyCodeUtils;

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
	@GetMapping(value = "/user/loginpage")
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/index");
		return modelAndView;
	}

	/**
	 * 登录动作
	 *
	 * @param user
	 * @param model
	 * @param rememberMe
	 * @return
	 */
	@PostMapping(value = "/login")
	public String login(Users user, Model model, String rememberMe, HttpServletRequest request) {
		// String codeMsg = (String) request.getAttribute("shiroLoginFailure");
		// if ("code.error".equals(codeMsg)) {
		// model.addAttribute("message", "验证码错误");
		// return "/login";
		// }
		// UsernamePasswordToken token = new
		// UsernamePasswordToken(user.getUsername().trim(),
		// user.getPassword());
		// Subject subject = ShiroUtil.getSubject();
		// String msg = null;
		// try {
		// subject.login(token);
		// //subject.hasRole("admin");
		// if (subject.isAuthenticated()) {
		// return "redirect:/main";
		// }
		// } catch (UnknownAccountException e) {
		// msg = "用户名/密码错误";
		// } catch (IncorrectCredentialsException e) {
		// msg = "用户名/密码错误";
		// } catch (ExcessiveAttemptsException e) {
		// msg = "登录失败多次，账户锁定10分钟";
		// }
		// if (msg != null) {
		// model.addAttribute("message", msg);
		// }
		return "redirect:/main/main";
		// return "/login";
	}

	/**
	 * 验证码生成功能
	 * 
	 * @param response
	 * @param request
	 */
	@GetMapping(value = "/getCode")
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
			session.setAttribute("_code", verifyCode.toLowerCase());
			// 生成图片
			int w = 146, h = 33;
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
