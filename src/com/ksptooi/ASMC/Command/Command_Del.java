package com.ksptooi.ASMC.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

public class Command_Del extends Command_cmd{

	
	
	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		

		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		MessageManager msg=ASMC.getMessageManager();
		
		msg.sendSysMessage("ÊäÈëÒªÉ¾³ıµÄÃüÁî");
		
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

