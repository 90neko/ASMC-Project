package com.ksptooi.asmc.service.command;

import java.util.HashMap;

import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

public class CommandTypeRegister implements CommandTypeRegisterService{

	private HashMap<String,Command_cmd> registerCommandType = new HashMap<String,Command_cmd>();

	
	
	private Logger logger = Asmc.getLogger();
	
	public CommandTypeRegister() {
		logger.info("初始化内部组件 - CommandTypeRegisterService[CTRS]");
	}
	
	
	
	@Override
	public boolean regCommandType(String typeName, Command_cmd commandType) {
		
		if(registerCommandType.get(typeName) != null) {
			logger.error("注册命令类型:"+typeName+"时失败! 命令类型名重复.");	
			return false;
		}
		

		registerCommandType.put(typeName, commandType);
		logger.info("注册命令类型:"+typeName+"成功.");	
		
		return true;
	}
	
	
	

	@Override
	public Command_cmd getCommandType(String typeName) throws Exception{	
		
		
		Command_cmd command_cmd = registerCommandType.get(typeName);
		
		if(command_cmd == null) {
			throw new RuntimeException();
		}
			
		
		return command_cmd;
	}
	
	
	
	
	

}
