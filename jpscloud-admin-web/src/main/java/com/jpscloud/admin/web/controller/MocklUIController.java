package com.jpscloud.admin.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api")
public class MocklUIController {

	@GetMapping(value = "/currentUser")
	public Map<String,String> currentUser() {
		
		Map<String,String> result = new HashMap<>();
		result.put("name", "Serati Ma");
		result.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png");
		result.put("userid", "00000001");
		result.put("notifyCount", "12");
		return result;
	}
	
	@GetMapping(value = "/fake_chart_data")
	public Map<String,String> fakeChartData() {
		Map<String,String> result = new HashMap<>();
		return result;
	}
	
	@PostMapping(value = "/login/account")
	public Map<String,String> login(){
		Map<String,String> result = new HashMap<>();
		result.put("status", "ok");
		result.put("currentAuthority", "admin");
		return result;
	}
	
}
