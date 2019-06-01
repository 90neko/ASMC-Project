package com.ksptooi.ASMC.event;

import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Entity.CommandEntity;

public class CommandEvent {

	

	private final String eventName = "CommandEvent";
	
	private CommandEntity commandEntity=null;
	
	private Command_cmd commandType=null;

	private boolean isCancel = false;
	
	private boolean isCommit = false;
	
	
	
	public CommandEvent(Command_cmd CommandType,CommandEntity commandEntity){
		
		this.commandType=CommandType;
		this.commandEntity=commandEntity;
		
	}
	
	
	
	//��������¼�
	public void commitEvent(){
			
		//������¼��Ѿ���ǰһ�������� ��ִ���κβ���
		if(isCommit == true){
			return;
		}
		
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
		
		if(isCancel == false) {
			return;
		}
		
		
		this.isCancel = isCancel;
	}

	public Command_cmd getCommandType() {
		return commandType;
	}

	
	public String getEventName() {
		return eventName;
	}
	
}
