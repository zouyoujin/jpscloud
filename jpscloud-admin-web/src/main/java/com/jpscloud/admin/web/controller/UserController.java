package com.jpscloud.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpscloud.admin.web.service.UsersService;
import com.jpscloud.common.entity.Users;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping(value = "/test")
	public String test() {
		Users user = usersService.selectById(1L);
		log.info("user = " + user);
		return "hello spring boot.";
	}
}
