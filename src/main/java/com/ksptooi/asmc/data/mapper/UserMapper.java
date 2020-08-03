package com.ksptooi.asmc.data.mapper;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.user.User;

public interface UserMapper {
	
	
	/**
	 * 根据用户名查询用户
	 */
	public ArrayList<User> queryUserByName(String user);
	
	/**
	 * 查询所有用户
	 */
	public ArrayList<User> queryUser();
	
	/**
	 * 添加一个用户
	 */
	public void addUser(User user);
	
	/**
	 * 删除用户
	 */
	public void delUser(String user);

}
