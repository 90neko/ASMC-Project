package com.ksptooi.asmc.service.user;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.user.User;

public interface UserDataService {
	
	
	/**
	 * 添加用户
	 */
	public void insertUser(User user);
	
	/**
	 * 删除用户
	 */
	public void deleteUser(int id);
	
	/**
	 * 修改用户
	 */
	public void updateUser(User user);
	
	/**
	 * 查询用户
	 */
	public ArrayList<User> getUserList(User user);
	
	/**
	 * 查询用户
	 */
	public User getUser(User user);
	
	/**
	 * 查询用户
	 */
	public User getUser(String userName);
	
	

}
