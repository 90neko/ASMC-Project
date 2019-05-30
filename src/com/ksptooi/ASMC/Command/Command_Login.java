package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;

public class Command_Login implements Command_cmd{

	@Override
	public void ExecuteOfType(CommandEntity ce) {

		
		if( ! ASMC.getUsermanager().isHaveAccess(ce)){
			return;
		}
		
		
		ASMC.getUsermanager().userLogin();

	}

	@Override
	public Command_cmd getThis() {
		return this;
	}

}
