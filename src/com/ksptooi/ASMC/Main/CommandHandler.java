package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.IOException;

import com.ksptooi.ASMC.Command.CommandTools;
import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.event.CommandEvent;

public class CommandHandler{
	
	
	
	public void ExecuteCommand() throws IOException{
		
		
		BufferedReader br=ASMC.getBr();
		CommandManager cm=ASMC.getCommandManager();
		MessageManager msg=new MessageManager();
		
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
				msg.sendWarningMessage("'"+Command+"'"+"不是有效的ASMC命令");
				continue;
			}
			
			ce=cm.getCommandByName(Command);
			
			
			//查询命令类型
			Command_cmd CT= CommandTools.getType(ce.getType());

			
			if(CT == null){
				msg.sendWarningMessage("'"+Command+"'"+"不是有效的ASMC命令");
				continue;
			}		


			ce.setPreCommand(PreCommand);
			
//			//根据命令类型执行命令
//			CT.ExecuteOfType(ce);
			
				
			
			//创建事件
			CommandEvent event=new CommandEvent(CT, ce);
			
			//执行事件
			ASMC.getEventmanager().startCommandEvent(event);

			
		}
			
		
	}
	
	
	
	
}
