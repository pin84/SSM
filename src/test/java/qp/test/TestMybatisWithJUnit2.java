package qp.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.qp.service.UserServiceI;

import qp.model.User;

public class TestMybatisWithJUnit2 {

	private ApplicationContext ac;
	private UserServiceI userServiceI;

	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring.xml", "classpath:spring-mybatis.xml" });
		userServiceI = (UserServiceI) ac.getBean("userService");
	}

	// 普通的junit4测试
	@Test
	public void test1() {
		User u = userServiceI.selectByPrimaryKey(1);
		System.out.println(u);
	}

}
