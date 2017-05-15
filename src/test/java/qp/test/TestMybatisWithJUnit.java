package qp.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qp.service.UserServiceI;

import qp.model.User;


public class TestMybatisWithJUnit {
	
	// 普通的junit4测试
	@Test
	public void test1() {
		// 通过配置文件获得spring的上下文
		ApplicationContext ac = new ClassPathXmlApplicationContext
				(new String[] { "spring.xml", "spring-mybatis.xml" });
		UserServiceI us = (UserServiceI) ac.getBean("userService");
		User u = us.selectByPrimaryKey(1);
		System.out.println(u);
	
	}
	
}
