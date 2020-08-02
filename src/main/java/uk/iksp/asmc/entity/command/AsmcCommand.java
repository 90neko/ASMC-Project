package uk.iksp.asmc.entity.command;

import uk.iksp.asmc.command.type.Command_cmd;

public class AsmcCommand extends CommandEntity{

	
	private Command_cmd commandType = null;

	
	
	public AsmcCommand(){
		
	}
	
	
	public Command_cmd getCommandType() {
		return commandType;
	}

	public void setCommandType(Command_cmd commandType) {
		this.commandType = commandType;
	}
	
	
	
	
}
