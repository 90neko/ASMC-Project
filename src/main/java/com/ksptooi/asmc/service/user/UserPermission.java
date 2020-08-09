package com.ksptooi.asmc.service.user;

import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.entity.user.User;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.service.event.EventBusService;


public class UserPermission implements UserPermissionService{

	
	User ActiveUser = null;
	
	
	EventBusService eventBusService = Asmc.getEventBusService();
	
	
	@Override
	public void setActiveUser(User user) {
		
		
		UserChangeEvent changeEvent = new UserChangeEvent(new User(),user);
		
		
		eventBusService.event(changeEvent);
		
		
		//切换用户
		this.ActiveUser = user;
		
	}

	@Override
	public User getActiveUser() {
		return this.ActiveUser;
	}

}
