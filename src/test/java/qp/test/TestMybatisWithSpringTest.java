package qp.test;


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qp.service.UserServiceI;

import qp.model.User;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMybatisWithSpringTest {
	
	@Resource
	private UserServiceI userservice;

	@Test
	public void test() {
		User u = userservice.selectByPrimaryKey(1);
		System.out.println(u);
	}

}
