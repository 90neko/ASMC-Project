package uk.iksp.asmc.event.type;

import uk.iksp.asmc.entity.user.UserEntity;
import uk.iksp.asmc.event.basic.AsmcEvent;

public class ActiveUserChangeEvent extends AsmcEvent{

	
	
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



	@Override
	public void commit() {
		
		if(this.isCancel()){
			return;
		}
		
	}
	
	
	
}
