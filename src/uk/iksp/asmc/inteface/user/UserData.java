package uk.iksp.asmc.inteface.user;

import java.util.ArrayList;
import uk.iksp.asmc.entity.user.UserEntity;

public interface UserData {
	
	
	/**
	 * 根据用户名查询用户
	 */
	public ArrayList<UserEntity> queryUserByName(String user);
	
	/**
	 * 查询所有用户
	 */
	public ArrayList<UserEntity> queryUser();
	
	/**
	 * 添加一个用户
	 */
	public void addUser(UserEntity user);
	
	/**
	 * 删除用户
	 */
	public void delUser(String user);

}
