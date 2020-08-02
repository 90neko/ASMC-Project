package uk.iksp.asmc.event.basic;

public abstract class AsmcEvent {

	
	String eventName = "BasicEvent";
	
	boolean isCancel = false;
	
	boolean isCommit = false;
	
	
	public AsmcEvent(String eventName){
		this.eventName=eventName;
	}
	
	public AsmcEvent(){
		
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
	
	/**
	 * 提交事件
	 */
	public abstract void commit();
	
	
}
