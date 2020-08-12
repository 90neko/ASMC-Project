package com.ksptooi.asmc.service.command;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.main.Asmc;

import uk.iksp.asmc.command.exception.CommandFormatException;

public class CommandParser implements CommandParserService{

	
	public CommandParser(){
		Asmc.getLogger().info("初始化内部组件 - CommandParserService[CPAS]");
	}

	
	
	/**
	 * 进行命令格式化
	 */	
	public String format(String preCommand) throws CommandFormatException{
		
		String formatCommand = null;
		
		//判断命令是否为空	
		if(/**preCommand.contains(" ")|**/preCommand.equals("")){
			throw new CommandFormatException("命令不能为空!");
		}
		
		//去除命令中的空格
		formatCommand = preCommand.replace("", "");
		
		
		return formatCommand;
	}
	
	
	
	/**
	 * 进行命令解析
	 */
	public InputCommand parseAsInputCommand(String command){
		
		
		String name = null;
		
		String parameterString = null;
		
		InputCommand ic = new InputCommand();
		
		ArrayList<String> al=new ArrayList<String>();
		
		//解析名字
		name = command.trim().split(">")[0];
		ic.setName(name);
		
		//解析参数
		try{
			
			parameterString = command.trim().split(">")[1];
			String[] parameter = parameterString.split(",");
			
			for(String str:parameter){
				al.add(str);
			}
			
			ic.setParameter(al);
			
		}catch(Exception e){
			ic.setParameter(al);
		}
		
		return ic;
	}
	

	
	
	
}
