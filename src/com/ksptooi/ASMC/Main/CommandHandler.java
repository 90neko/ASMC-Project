package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.IOException;

import uk.iksp.asmc.command.services.CommandService;
import uk.iksp.asmc.command.services.CommandTools;
import uk.iksp.asmc.command.type.Command_cmd;
import uk.iksp.asmc.entity.command.CommandEntity;
import uk.iksp.asmc.event.type.CommandEvent;

public class CommandHandler{
	
	
	
	
	
	public void ExecuteCommand() throws IOException{
		
		
		BufferedReader br=ASMC.getBr();
		
		CommandService service = ASMC.getCommandService();
		
		while(true){
			
			System.out.println("");
			
//			System.out.print("ASMC@"+ASMC.getUserManager().getActiveUser().getAccount()+":");
			
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
			if(!service.isExistsCommand(Command)){
				
				//创建事件 - 未知命令
				ASMC.getEventmanager().startUnknowCommandEvent(PreCommand);
				
				continue;
			}
			
			ce=service.getCommand(Command);
			
			
			//查询命令类型
			Command_cmd CT= CommandTools.getType(ce.getCm_Type());

			
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
