package com.qp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/welcome")
	public String loginSuccess() {
		return "welcome";
	}
	
	@RequestMapping("/query")
	public String query() {
		return "query";
	}
	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
}
