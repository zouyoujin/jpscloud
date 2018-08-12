package com.jpscloud.admin.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpscloud.common.entity.Menus;
import com.jpscloud.common.utils.ResponseService;

/**
 * 
 * @ClassName: MenuController   
 * @Description: 菜单管理页面功能
 * @author: Kitty
 * @date: 2018年8月5日 下午11:27:00   
 *
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	
	/**
	 * 根据用户id获取用户菜单信息
	 * @return
	 */
	@GetMapping(value = "/getMenusByUserId")
	public ResponseService<List<Menus>> getMenusByUserId() {
		List<Menus> list = new ArrayList<>();
		//打桩数据
		Menus m1 = new Menus(1L, null, "dashboard", "dashboard", "dashboard");
		Menus m11 = new Menus(2L, 1L, "分析页", null, "analysis");
		Menus m12 = new Menus(3L, 1L, "监控页", null, "monitor");
		Menus m13 = new Menus(4L, 1L, "工作台", null, "workplace");
		List<Menus> children = new ArrayList<>();
		children.add(m11);
		children.add(m12);
		children.add(m13);
		m1.setChildren(children);
		list.add(m1);
		
		ResponseService<List<Menus>> result = new ResponseService<>();
		result.setData(list);
		return result;
	}
}
