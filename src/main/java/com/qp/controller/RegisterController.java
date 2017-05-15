package com.qp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qp.service.UserServiceI;

import net.sf.json.JSONObject;
import qp.model.User;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Resource
	private UserServiceI userService;

	// 检查用户名
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public String checkName(String userName_1) {
		User u = userService.getUserByName(userName_1);
		boolean flag = true;
		if (u == null) {
			flag = false;
		}
		// 将数据转成json
		Map<String, Object> map = new HashMap<>();
		map.put("flag", flag);
		String json = JSONObject.fromObject(map).toString();
		return json;
	}

	// 检查邮箱
	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public String checkEmail(String email_1) {
		User u = userService.getUserByEmail(email_1);
		boolean flag = true;
		if (u == null) {
			flag = false;
		}
		// 将数据转成json
		Map<String, Object> map = new HashMap<>();
		map.put("flag", flag);
		String json = JSONObject.fromObject(map).toString();
		return json;
	}

	// 注册成功
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView registerSuccess(String username, String email_name, String password_name) {
		// 新增用户
		User u = new User();
		u.setName(username);
		u.setPwd(password_name);
		u.setEmail(email_name);
		userService.insert(u);

		Map<String, Object> map = new HashMap<>();
		map.put("usermap", u);
		return new ModelAndView("/registerSuccess", map);
	}

}
