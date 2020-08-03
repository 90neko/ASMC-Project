package com.ksptooi.asmc.entity.commandType;

import com.ksptooi.asmc.entity.command.Command;


public interface Command_cmd {

	
	public void ExecuteOfType(Command cmd);
	
	//返回自身
	public Command_cmd getThis();
	
}
