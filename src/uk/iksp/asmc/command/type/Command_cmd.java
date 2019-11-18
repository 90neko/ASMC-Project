package uk.iksp.asmc.command.type;


import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.auth.AuthManager;

import uk.iksp.asmc.entity.command.CommandEntity;

public interface Command_cmd {

	MessageManager msg=ASMC.getMessageManager();
	AuthManager um=ASMC.getUserManager();
	
	

	public void ExecuteOfType(CommandEntity ce);
	
	//返回自身
	public Command_cmd getThis();
	
	
}
