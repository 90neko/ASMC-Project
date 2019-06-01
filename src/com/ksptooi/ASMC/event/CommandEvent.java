package com.ksptooi.ASMC.event;

import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Entity.CommandEntity;

public class CommandEvent {

	
	
	private CommandEntity commandEntity=null;
	
	private Command_cmd commandType=null;
	
	private boolean isCancel = false;
	
	private boolean isCommit = false;
	
	
	
	public CommandEvent(Command_cmd CommandType,CommandEntity commandEntity){
		
		this.commandType=CommandType;
		this.commandEntity=commandEntity;
		
	}
	
	
	

	
	//立即完成事件
	public void commitEvent(){
			
		commandType.ExecuteOfType(commandEntity);
		
		this.isCommit = true;
		
	}
	
	
	public boolean isCommit(){
		return isCommit;
	}
	
	
	public CommandEntity getCommandEntity() {
		return commandEntity;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public Command_cmd getCommandType() {
		return commandType;
	}

	
	
}
