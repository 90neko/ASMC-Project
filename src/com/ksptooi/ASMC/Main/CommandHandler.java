package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.IOException;

import com.ksptooi.ASMC.Command.CommandTools;
import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.event.CommandEvent;

public class CommandHandler{
	
	
	
	
	
	public void ExecuteCommand() throws IOException{
		
		
		BufferedReader br=ASMC.getBr();
		CommandManager cm=ASMC.getCommandManager();
		
		while(true){
			
			
			System.out.println("");
			
			System.out.print("ASMC@"+ASMC.getUserManager().getActiveUser().getAccount()+":");
			
			String PreCommand=br.readLine();
			
			String Command=null;
			
			CommandEntity ce=null;
			
			//进行命令预处理	
//			if(PreCommand.contains("")|PreCommand.equals("")){
//				continue;
//			}
			
			if(PreCommand.equals("")){
				continue;
			}
			
			Command = PreCommand.trim().split(">")[0];
			
			
			//预处理 - end
			
			
			//创建预命令事件
			boolean isPreCommandSuccess = ASMC.getEventmanager().startPreCommandEvent(Command);
			
			if(!isPreCommandSuccess) {
				continue;
			}
			
						
			//检查命令是否存在
			if(!cm.isExistsCmd(Command)){
				
				//创建事件 - 未知命令
				ASMC.getEventmanager().startUnknowCommandEvent(PreCommand);
				
				continue;
			}
			
			ce=cm.getCommandByName(Command);
			
			
			//查询命令类型
			Command_cmd CT= CommandTools.getType(ce.getType());

			
			if(CT == null){
				//创建事件 - 未知命令
				ASMC.getEventmanager().startUnknowCommandEvent(PreCommand);
				continue;
			}


			ce.setPreCommand(PreCommand);
		
			//创建事件
			CommandEvent event=new CommandEvent(CT, ce);
			
			//执行事件
			ASMC.getEventmanager().startCommandEvent(event);

			
		}
			
		
	}
	
	
	
	
}
