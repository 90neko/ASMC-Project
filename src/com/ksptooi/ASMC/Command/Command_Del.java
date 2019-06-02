package com.ksptooi.ASMC.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

public class Command_Del implements Command_cmd{

	
	
	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		if( ! ASMC.getUserManager().isHaveAccess(ce)){
			return;
		}
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		MessageManager msg=ASMC.getMessageManager();
		
		msg.sendSysMessage("输入要删除的命令");
		
		try {
			
			String Command = br.readLine();
				
			ASMC.getCommandManager().delCommand(Command);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	@Override
	public Command_cmd getThis() {
		return this;
	}
	
}

