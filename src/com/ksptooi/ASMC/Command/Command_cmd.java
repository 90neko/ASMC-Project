package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Data.SqlDAO;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.auth.AuthManager;

public abstract class Command_cmd {

	public MessageManager msg=ASMC.getMessageManager();
	public CommandManager cmm=ASMC.getCommandManager();
	public AuthManager um=ASMC.getUserManager();
	public SqlDAO dao=ASMC.getSqlmanager().getDAO();
	
	
	
	

	public abstract void ExecuteOfType(CommandEntity ce);
	
	//·µ»Ø×ÔÉí
	public abstract Command_cmd getThis();
	
	
	
	
}
