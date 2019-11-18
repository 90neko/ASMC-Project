package com.ksptooi.ASMC.event;

import uk.iksp.asmc.entity.user.UserEntity;

public class ActiveUserChangeEvent extends BasicEvent{

	
	
	private final String eventName = "ActiveUserChangeEvent";
	

	private UserEntity oldActiveUser=null;
	
	private UserEntity changeToUser=null;
	
	private boolean isCancel = false;

	
	public ActiveUserChangeEvent(UserEntity oldUser,UserEntity newUser) {
		
		this.oldActiveUser=oldUser;
		this.changeToUser=newUser;
		
	}
	
	
	
	public boolean isCancel() {
		return isCancel;
	}



	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public UserEntity getOldActiveUser() {
		return oldActiveUser;
	}



	public void setOldActiveUser(UserEntity oldActiveUser) {
		this.oldActiveUser = oldActiveUser;
	}




	public UserEntity getChangeToUser() {
		return changeToUser;
	}




	public void setChangeToUser(UserEntity changeToUser) {
		this.changeToUser = changeToUser;
	}


	
	public String getEventName() {
		return eventName;
	}
	
	
	
}
