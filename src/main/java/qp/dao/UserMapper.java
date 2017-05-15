package qp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import qp.model.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	// int insertSelective(User record);

	User selectByPrimaryKey(int id);

	int updateByPrimaryKeySelective(User record);

	// int updateByPrimaryKey(User record);

	// 以下为自己写的
	User getUserByName(String name);

	User getUserByEmail(String email);

	User findByNameAndPwd(@Param(value = "name") String name, @Param(value = "pwd") String pwd);

	List<User> queryUser(@Param(value = "name") String name, @Param(value = "pageIndex") int pageIndex,
			@Param(value = "pageSize") int pageSize);

	List<User> userList(@Param(value = "pageIndex") int pageIndex, @Param(value = "pageSize") int pageSize);

}