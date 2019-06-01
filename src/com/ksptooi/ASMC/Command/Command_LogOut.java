package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;

public class Command_LogOut implements Command_cmd{

	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		if( ! ASMC.getUserManager().isHaveAccess(ce)){
			return;
		}
		
		
		ASMC.getUserManager().setActiveUser(ASMC.getUserManager().getUser("user"));
		
	}
	
	
	

	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
}
