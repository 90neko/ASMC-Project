package com.ksptooi.asmc.entity.event;

public class CommandEvent extends AbstractEvent{

	@Override
	public String getName() {
		return "CommandEvent";
	}

	@Override
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	
	private String command = null;
	private String[] parms = null;
	
	
	public CommandEvent(String command,String[] parms) {
		this.command = command;
		this.parms = parms;
	}

	
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String[] getParms() {
		return parms;
	}

	public void setParms(String[] parms) {
		this.parms = parms;
	}
	
	

}
