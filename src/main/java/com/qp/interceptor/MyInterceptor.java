package com.qp.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qp.cookie.CookieTool;
import com.qp.service.UserServiceI;
import com.qp.service.UserServiceImpl;

import qp.dao.UserMapper;
import qp.model.User;

public class MyInterceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		
		String requestUri = request.getRequestURI(); // 请求完整路径，可用于登陆后跳转
		String contextPath = request.getContextPath(); // 项目下完整路径
		String url = requestUri.substring(contextPath.length()); // 请求页面
//		System.out.print("发生拦截...");
//		System.out.println("来自：" + requestUri + "的请求");
//		System.out.println("请求页面=" + url);
		// User user = (User) request.getSession().getAttribute("currentUser");

		String[] noFilters = new String[] { "register", "index", "login" };// 不拦截的请求
		boolean beFilter = true;
		for (String s : noFilters) {
//			System.out.println(url.indexOf(s) + "======" + url);
			if (url.indexOf(s) != -1) {
//				System.out.println("beFilter = false");
				beFilter = false;
				break;
			}
		}
		System.out.println(beFilter + "准备完成preHandle，进入controller");
		if (beFilter == true) { // 除了以上不拦截的请求之外，都拦截
			// 取出页面中存在的Cookies
			Cookie cokLoginName = CookieTool.getCookieByName(request, "cookieName_name");// controller登录时添加
			Cookie cokLoginPwd = CookieTool.getCookieByName(request, "cookieName_pwd");// controller登录时添加

			if (cokLoginName != null && cokLoginPwd != null && cokLoginName.getValue() != ""
					&& cokLoginPwd.getValue() != "") { // 如果cookie存在，则拿出
				String loginName = cokLoginName.getValue();
				String loginPwd = cokLoginPwd.getValue();
				// 打印取出的cookie
				System.out.println("不为空 , loginName=" + loginName + ", loginPwd=" + loginPwd);

				// 根据客户端保存的用户名和密码从数据库中验证用户
				// 通过配置文件获得spring的上下文
				ApplicationContext ac = new ClassPathXmlApplicationContext
						(new String[] { "spring.xml", "spring-mybatis.xml" });
				UserServiceI userServiceI = (UserServiceI) ac.getBean("userService");
				User findUser = userServiceI.findByNameAndPwd(loginName, loginPwd);
				
//				System.out.println("查找数据库中的用户  findUser= " + findUser);
				
				if (findUser == null) { // 如果数据库没有此用户或已被删除
//					System.out.println("数据库没有此用户或已被删除");
					CookieTool.addCookie(response, "cookieName_name", null, 0); // 清除Cookie
					CookieTool.addCookie(response, "cookieName_pwd", null, 0); // 清除Cookie
					response.sendRedirect(contextPath+"/rest/index");// 返回登录页面
					return false;
				} else { // 如果数据库有此用户
//					System.out.println("如果数据库有此用户");
					User currentUser = (User) request.getSession().getAttribute("currentUser");
					if (currentUser == null) { // 如果未登录而直接拷贝地址栏进入页面
//						System.out.println("未登录而直接拷贝地址栏进入页面");
						request.getSession().setAttribute("currentUser", findUser);
						
					} else { // 用户登录后
//						System.out.println("用户登录后");
						if (currentUser.getName().equals(findUser.getName())) {// 如果当前登录人与cookie中信息一致
//							System.out.println("当前登录人与cookie中信息一致");
							request.getSession().setAttribute("currentUser", findUser);
						} else {// 如果当前登录人与cookie中信息不一致
//							System.out.println("当前登录人与cookie中信息不一致");
							request.getSession().setAttribute("currentUser", currentUser);
						}
					}
				}
			} else { // 如果页面中没有cookie ,或者是退出后清除了cookie
				User u = (User) request.getSession().getAttribute("currentUser");
				if (u == null) { // 如果未登录
//					System.out.println("u==null,如果未登录,即将返回登录页面");
					response.sendRedirect(contextPath+"/rest/index");
					return false;// 返回false那么preHandle方法将结束 ，进入controller
				} else {// 如果已经登录
					// 执行下一步
				}
			}
		}
		// 不拦截登录页面的请求，进入下一步。
//		System.out.println("preHandle执行完成");
		
		
		return true;
	}
	
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion");
	}

	

}
