package com.qp.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qp.cookie.CookieTool;
import com.qp.service.UserServiceI;

import qp.model.User;

@Controller
public class CommonController {

	@Resource
	UserServiceI userservice;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request) {

		// 跟据cookie保存的用户名和密码自动登录 ，如果cookie没有信息，则跳到登录页面
		Cookie cokLoginName = CookieTool.getCookieByName(request, "cookieName_name");// controller登录时添加
		Cookie cokLoginPwd = CookieTool.getCookieByName(request, "cookieName_pwd");// controller登录时添加
//		System.out.println("name=" + cokLoginName + ", pwd= " + cokLoginPwd);
		if (cokLoginName != null && cokLoginPwd != null) {
			String loginName = cokLoginName.getValue();
			String loginPwd = cokLoginPwd.getValue();
			User u = userservice.findByNameAndPwd(loginName, loginPwd);

			if (u != null) {
				return "welcome";
			}
		}
		return "index";
	}
}
