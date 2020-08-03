package uk.iksp.asmc.event.type;

import com.ksptooi.asmc.entity.user.User;

import uk.iksp.asmc.event.basic.AsmcEvent;

public class ActiveUserChangeEvent extends AsmcEvent{

	
	
	private final String eventName = "ActiveUserChangeEvent";
	

	private User oldActiveUser=null;
	
	private User changeToUser=null;
	
	private boolean isCancel = false;

	
	public ActiveUserChangeEvent(User oldUser,User newUser) {
		
		this.oldActiveUser=oldUser;
		this.changeToUser=newUser;
		
	}
	
	
	
	public boolean isCancel() {
		return isCancel;
	}



	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public User getOldActiveUser() {
		return oldActiveUser;
	}



	public void setOldActiveUser(User oldActiveUser) {
		this.oldActiveUser = oldActiveUser;
	}




	public User getChangeToUser() {
		return changeToUser;
	}




	public void setChangeToUser(User changeToUser) {
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
