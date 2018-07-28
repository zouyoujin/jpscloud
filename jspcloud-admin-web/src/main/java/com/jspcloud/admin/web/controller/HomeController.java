package com.jspcloud.admin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "")
public class HomeController {

	@GetMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
        mav.addObject("name", "jpscloud");
        mav.setViewName("index");
        return mav;
	}
}
