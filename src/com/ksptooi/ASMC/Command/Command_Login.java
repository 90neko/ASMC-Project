package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;

public class Command_Login implements Command_cmd{

	@Override
	public void ExecuteOfType(CommandEntity ce) {

		
		if( ! ASMC.getUserManager().isHaveAccess(ce)){
			return;
		}
		
		
		ASMC.getUserManager().userLogin();

	}

	@Override
	public Command_cmd getThis() {
		return this;
	}

}
