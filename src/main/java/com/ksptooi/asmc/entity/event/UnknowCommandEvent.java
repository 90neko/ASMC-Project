package com.ksptooi.asmc.entity.event;

import com.ksptooi.asmc.entity.command.InputCommand;

public class UnknowCommandEvent extends AbstractEvent{

	
	private InputCommand inputCommand = null;
	
	private String message = null;
	
	
	public UnknowCommandEvent(InputCommand inputCommand) {
		this.inputCommand = inputCommand;
		this.message = "'"+inputCommand.getName()+"'不是有效的AMTC命令或脚本!";
	}
	
	
	
	
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
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
