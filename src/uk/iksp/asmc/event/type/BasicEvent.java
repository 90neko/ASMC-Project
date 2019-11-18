package uk.iksp.asmc.event.type;

public class BasicEvent {

	
	String eventName = "BasicEvent";
	
	boolean isCancel = false;
	
	boolean isCommit = false;
	
	
	public BasicEvent(String eventName){
		this.eventName=eventName;
	}
	
	public BasicEvent(){
		
	}
	

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
			
		//阻止重新将cancel设置回false
		if(isCancel == false) {
			return;
		}
			
		this.isCancel = isCancel;
		
	}

	public boolean isCommit() {	
		return isCommit;
	}

	public void setCommit(boolean isCommit) {
		
		
		//阻止重新将commit设置回false
		if(isCommit == false) {
			return;
		}
		
		this.isCommit = isCommit;
	}
	
	
	
}
