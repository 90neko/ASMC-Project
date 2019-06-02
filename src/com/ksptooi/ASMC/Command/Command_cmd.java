package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Data.SqlDAO;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.auth.AuthManager;

public interface Command_cmd {

	MessageManager msg=ASMC.getMessageManager();
	CommandManager cmm=ASMC.getCommandManager();
	AuthManager um=ASMC.getUserManager();
	SqlDAO dao=ASMC.getSqlmanager().getDAO();
	
	
	
	

	public void ExecuteOfType(CommandEntity ce);
	
	//返回自身
	public Command_cmd getThis();
	
	

	
	
}
