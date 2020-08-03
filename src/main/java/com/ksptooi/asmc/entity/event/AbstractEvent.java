package com.ksptooi.asmc.entity.event;

public abstract class AbstractEvent {
	
	
	String name = "AbstractEvent";
	
	boolean isCancel = false;
	
	public abstract String getName();
	
	public abstract void setCancel(boolean isCancel);

	public boolean isCancel() {
		return isCancel;
	}

}
