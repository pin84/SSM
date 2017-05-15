package com.qp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qp.cookie.CookieTool;
import com.qp.service.UserServiceI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import qp.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserServiceI userService;

	// 登录
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(String username, String password, boolean isLogin, HttpServletRequest request,
			HttpServletResponse response) {
		User u = userService.getUserByName(username);
		boolean flag = false;
		if (u != null && password.equals(u.getPwd())) {
			flag = true;
			if (isLogin) {
				int loginMaxAge = 60 * 60 * 24 * 7; // 定义账户密码的生命周期。单位为秒.   
				CookieTool.addCookie(response, "cookieName_name", username, loginMaxAge);
				CookieTool.addCookie(response, "cookieName_pwd", password, loginMaxAge);
			}
			request.getSession().setAttribute("currentUser", u);
		}

		// 将数据转成json
		Map<String, Object> map = new HashMap<>();
		map.put("flag", flag);
		String json = JSONObject.fromObject(map).toString();
		return json;
	}

	// 退出
	@RequestMapping("/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CookieTool.addCookie(response, "cookieName_name", null, 0);
		CookieTool.addCookie(response, "cookieName_pwd", null, 0);
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		response.sendRedirect(basePath + "rest/index");
	}

	// 查询用户
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public String queryUser(String iname, int pageIndex, int pageSize) {
		List<User> list = userService.queryUser(iname, pageIndex, pageSize);
		String json = JSONArray.fromObject(list).toString();
		System.out.println(json);
		return json;
	}

	// 编辑用户
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public void edit(int id, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String ename = request.getParameter("ename");
		String epwd = request.getParameter("epwd");
		String email = request.getParameter("email");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		System.out.println(email + "," + province + "," + city);
		User u = userService.selectByPrimaryKey(id);
		u.setName(ename);
		u.setPwd(epwd);
		u.setEmail(email);
		u.setProvince(province);
		u.setCity(city);
		userService.updateByPrimaryKeySelective(u);

		request.getRequestDispatcher("/rest/page/query").forward(request, response);
	}

	@RequestMapping(value = "/edit1", method = RequestMethod.POST)
	@ResponseBody
	public User edit1(int id, String ename, String epwd) {
		User u = userService.selectByPrimaryKey(id);
		u.setName(ename);
		userService.updateByPrimaryKeySelective(u);
		System.out.println("user/edit  执行完成");
		return u;

	}

	// 删除用户
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public int userList(int id) {
		int i = userService.deleteByPrimaryKey(id);
		return i;
	}

	// 分页显示用户
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String pageUser(int pageIndex, int pageSize) {
		List<User> listUsers = userService.userList(pageIndex, pageSize);
		String json = JSONArray.fromObject(listUsers).toString();
		return json;
	}

}
