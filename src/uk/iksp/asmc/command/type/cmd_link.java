package uk.iksp.asmc.command.type;

import uk.iksp.asmc.entity.command.CommandEntity;

public class cmd_link implements Command_cmd {

	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		System.out.println("HelloWorld");
		
	}

	@Override
	public Command_cmd getThis() {
		return this;
	}

}
