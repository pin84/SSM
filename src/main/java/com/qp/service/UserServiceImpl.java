package com.qp.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qp.dao.UserMapper;
import qp.model.User;

@Service("userService")
public class UserServiceImpl implements UserServiceI {

	@Resource
	private UserMapper userMapper;

	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	@Override
	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}
	
	@Override
	public int deleteByPrimaryKey(int id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	public List<User> queryUser(String name,int pageIndex, int pageSize) {
		return userMapper.queryUser(name,pageIndex,  pageSize);
	}

	@Override
	public List<User> userList(int pageIndex, int pageSize) {
		return userMapper.userList(pageIndex, pageSize);
	}

	@Override
	public User findByNameAndPwd(String name, String pwd) {
		return userMapper.findByNameAndPwd(name, pwd);
	}

	@Override
	public User selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKeySelective(record);
	}

}
