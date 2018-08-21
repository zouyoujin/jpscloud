package com.jpscloud.admin.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.jpscloud.admin.web.service.UsersService;
import com.jpscloud.common.entity.Users;
import com.jpscloud.common.utils.JsonUtils;
import com.jpscloud.common.vo.PageList;
import com.jpscloud.common.vo.ResponseService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private UsersService usersService;

	/**
	 * 用户分页列表展示
	 * 
	 * @return
	 */
	@GetMapping(value = "/getUsersPage")
	public ResponseService<PageList<Users>> getUsersPage(@RequestParam(defaultValue = "1") int current,
			@RequestParam(defaultValue = "20") int pageSize) {
		ResponseService<PageList<Users>> response = new ResponseService<PageList<Users>>();
		Page<Users> page = new Page<>(current, pageSize);
		response.setData(usersService.getUserPage(page));
		return response;
	}

	@GetMapping(value = "/test")
	public String test() {
		Users user = usersService.selectById(1L);
		log.info("user = " + user);
		List<Users> list = usersService.getAllUser();
		log.info(JsonUtils.toJson(list));
		list = usersService.getAllUserFromSaveDB();
		log.info(JsonUtils.toJson(list));
		return "hello spring boot.";
	}

	@PostMapping(value = "/add")
	public String add(@Valid Users user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return bindingResult.getFieldError().getDefaultMessage();
		} else {
			return "添加成功，" + user;
		}
	}
}
