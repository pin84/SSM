package com.qp.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import qp.model.User;

public interface UserServiceI {

//	public User getUserById(int id);
	public int deleteByPrimaryKey(int id);
//	public int insertSelective(User record);
	public int insert(User record);
	public int updateByPrimaryKeySelective(User record);
	
	public User selectByPrimaryKey(int id);
	
	
	public User getUserByName(String name);
	public User getUserByEmail(String email);
	
	public User findByNameAndPwd(String name, String pwd);
	public List<User> queryUser(String name,int pageIndex, int pageSize);
	public List<User> userList(int pageIndex, int pageSize);
	
}
