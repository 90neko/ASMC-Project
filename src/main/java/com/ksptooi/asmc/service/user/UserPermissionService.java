package com.ksptooi.asmc.service.user;

import com.ksptooi.asmc.entity.user.User;

public interface UserPermissionService {
	
	
	/**
	 * 切换当前活动用户
	 */
	public void setActiveUser(User user);
	
	/**
	 * 获取当前活动用户
	 */
	public User getActiveUser();
	

}
