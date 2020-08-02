package uk.iksp.asmc.command.type;

import uk.iksp.asmc.entity.command.CommandEntity;

public interface Command_cmd {

	
	public void ExecuteOfType(CommandEntity ce);
	
	//返回自身
	public Command_cmd getThis();
	
}
