package com.ksptooi.ASMC.event;

public class PreCommandEvent {
	
	

	private final String eventName = "PreCommandEvent";
	
	
	private String PreCommand=null;
	

	private boolean isCancel = false;
	
	
	public PreCommandEvent(String PreCommand) {
		this.PreCommand=PreCommand;
	}
	
	
	
	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		
		if(isCancel == false) {
			return;
		}
		
		
		this.isCancel = isCancel;
	}
	
	public String getPreCommand() {
		return PreCommand;
	}


	public String getEventName() {
		return eventName;
	}
	
}
