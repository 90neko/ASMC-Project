package com.ksptooi.asmc.entity.event;

import com.ksptooi.asmc.entity.command.Command;

public class CommandEvent extends AbstractEvent{

	@Override
	public String getName() {
		return "CommandEvent";
	}

	@Override
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}
	
	
	Command command = null;
	
	
	
	public CommandEvent(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	
	
	
	

}
