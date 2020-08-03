package com.ksptooi.asmc.service.user;

import com.ksptooi.asmc.entity.user.User;
import com.ksptooi.asmc.main.Asmc;

import uk.iksp.asmc.event.type.ActiveUserChangeEvent;

public class UserPermission implements UserPermissionService{

	
	User ActiveUser = null;
	
	@Override
	public void setActiveUser(User user) {
		
		
		//调用事件
		boolean isSuccess = Asmc.getEventCreate().startActiveUserChangeEvent(new ActiveUserChangeEvent(ActiveUser,user));
		
		
		//如果事件被取消则不切换用户
		if( ! isSuccess){
			return;
		}
		
		
		//切换用户
		this.ActiveUser = user;
		
	}

	@Override
	public User getActiveUser() {
		return this.ActiveUser;
	}

}
