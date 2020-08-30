package com.ksptooi.asmc.entity.event;

import com.ksptooi.asmc.entity.user.User;

public class UserChangeEvent extends AbstractEvent{

	
	private User oldUser = null;
	private User newUser = null;
	
	
	public UserChangeEvent(User oldUser,User newUser) {
		this.oldUser = oldUser;
		this.newUser = newUser;
	}
	
	
	
	
	@Override
	public String getName() {
		return "UserChangeEvent";
	}
	
	
	@Override
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	

	

	public User getOldUser() {
		return oldUser;
	}


	public void setOldUser(User oldUser) {
		this.oldUser = oldUser;
	}


	public User getNewUser() {
		return newUser;
	}


	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}





	
	
	
	
	
	
}
