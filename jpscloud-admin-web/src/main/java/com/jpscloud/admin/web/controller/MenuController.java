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
		Menus m1 = new Menus(1L, null, "dashboard", "dashboard", "/dashboard");
		Menus m11 = new Menus(2L, 1L, "分析页344", null, "/dashboard/analysis");
		Menus m12 = new Menus(3L, 1L, "监控页", null, "/dashboard/monitor");
		Menus m13 = new Menus(4L, 1L, "工作台", null, "/dashboard/workplace");
		List<Menus> children = new ArrayList<>();
		children.add(m11);
		children.add(m12);
		children.add(m13);
		m1.setChildren(children);
		list.add(m1);
		
		Menus m2 = new Menus(5L, null, "表单页", "form", "/form");
		Menus m21 = new Menus(6L, 5L, "基础表单", null, "/form/basic-form");
		Menus m22 = new Menus(7L, 5L, "分步表单", null, "/form/step-form");
		Menus m23 = new Menus(8L, 5L, "高级表单", null, "/form/advanced-form");
		List<Menus> children2 = new ArrayList<>();
		children2.add(m21);
		children2.add(m22);
		children2.add(m23);
		m2.setChildren(children2);
		list.add(m2);
		
		ResponseService<List<Menus>> result = new ResponseService<>();
		result.setData(list);
		return result;
	}
}
