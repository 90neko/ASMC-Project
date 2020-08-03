package com.ksptooi.asmc.entity.event;

import uk.iksp.asmc.entity.command.InputCommand;

public class UnknowCommandEvent extends AbstractEvent{

	
	private InputCommand inputCommand = null;
	private String Message = null;
	
	
	@Override
	public String getName() {
		return "UnknowCommandEvent";
	}

	/**
	 * 这个事件一旦创建无法被取消 直到所有的EventHandler处理完
	 */
	@Override
	public void setCancel(boolean isCancel) {
		
	}
	
	
	
	
	
	public InputCommand getInputCommand() {
		return inputCommand;
	}
	

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}
	
	
	

}
