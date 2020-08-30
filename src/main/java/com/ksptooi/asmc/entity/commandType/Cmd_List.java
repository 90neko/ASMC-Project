package com.ksptooi.asmc.entity.commandType;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandDataService;

public class Cmd_List implements Command_cmd{

	@Override
	public void ExecuteOfType(Command command) {
		
		Logger msg = Asmc.getLogger();
		
		CommandDataService ms = Asmc.getCommandDataService();
		
		ArrayList<Command> commandList = ms.queryList(null);
		
		String R2="|   命令名  | 是否为目录 |    类型    |     描述    |  CCA  |";
		
		System.out.println(R2);
		
		
		String name="";
		String type="";
		String path="";
		String title="";
		String CCA="";		
		msg.sendBr();
		
		for(Command ce:commandList){
			
			boolean isDir = ce.getFile().equalsIgnoreCase("");
			
			name="|"+ce.getName();
			type=""+isDir;
			path=""+ce.getType();
			title=""+ce.getTitle();
			CCA=""+ce.getCca();
			
			
			while(name.length()<=9){
				name=name+" ";
			}
			name=name+"|";
			
			while(type.length()<=9){
				type=type+" ";
			}
			type=type+"|";
			
			while(path.length()<=12){
				path=path+" ";
			}
			path=path+"|";
			
			while(title.length()<=12){
				title=title+" ";
			}
			title=title+"|";
			
			while(CCA.length()<=8){
				CCA=CCA+" ";
			}
			CCA=CCA+"|";
			
			
			System.out.println(name+type+path+title+CCA);	
			
		}
		
		
		
	}

	
	
	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}





	
	
	
	
}
