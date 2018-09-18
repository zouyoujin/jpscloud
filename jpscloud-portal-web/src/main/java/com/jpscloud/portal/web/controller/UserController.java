package com.jpscloud.portal.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jpscloud.common.vo.ResponseService;
import com.jpscloud.portal.web.constant.Constant;
import com.jpscloud.portal.web.entity.Members;
import com.jpscloud.portal.web.service.MembersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "")
@Api(value="用户controller",tags={"用户操作接口"})
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MembersService membersService;

	/**
	 * 用户首页
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/user/index")
	public ModelAndView index(HttpServletRequest request) {
		log.info("UserController index");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/index");
		return modelAndView;
	}

	/**
	 * 用户主页
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/user/home")
	public ModelAndView home(HttpServletRequest request) {
		log.info("UserController home");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/home");
		return modelAndView;
	}

	/**
	 * 用户设置
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/user/set")
	public ModelAndView set(HttpServletRequest request) {
		log.info("UserController set");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/set");
		return modelAndView;
	}

	/**
	 * 用户消息
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/user/message")
	public ModelAndView message(HttpServletRequest request) {
		log.info("UserController message");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/message");
		return modelAndView;
	}

	/**
	 * 注册处理
	 * 
	 * @return
	 */
	@ApiOperation(value="用户注册", notes="根据Member对象注册用户")
	@PostMapping(value = "/user/register")
	public ResponseService<Boolean> register(@ApiParam(name="用户对象",value="用户注册相关字段",required=true) Members member,
			@RequestParam(name = "vercode", required = false) String vercode, BindingResult bindingResult,HttpServletRequest request) {
		ResponseService<Boolean> response = new ResponseService<Boolean>();
		try {
			// 存入会话session
			HttpSession session = request.getSession(true);
			String sessionVercode = (String) session.getAttribute(Constant.Register.VERIFYCODE_KEY);
			if(!sessionVercode.equals(vercode.toLowerCase())){
				response.setStatus(Constant.Status.VERIFY_ERROR);
				response.setData(false);
				response.setMessage("输入验证码不正确!");
				return response;
			}
			if (bindingResult.hasErrors()) {
				response.setStatus(Constant.Status.VERIFY_ERROR);
				response.setData(false);
				response.setMessage(bindingResult.getFieldError().getDefaultMessage());
				return response;
			}
			response = membersService.register(member);
		} catch (Exception e) {
			log.error("reg fail." + e);
			response.setMessage(e.getMessage());
			response.setData(false);
		}
		return response;
	}

}
