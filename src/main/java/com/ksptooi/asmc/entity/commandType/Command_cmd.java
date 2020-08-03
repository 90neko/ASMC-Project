package com.ksptooi.asmc.entity.commandType;

import uk.iksp.asmc.entity.command.CommandEntity;

public interface Command_cmd {

	
	public void ExecuteOfType(CommandEntity ce);
	
	//返回自身
	public Command_cmd getThis();
	
}
