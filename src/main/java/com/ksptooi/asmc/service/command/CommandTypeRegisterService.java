package com.ksptooi.asmc.service.command;

import com.ksptooi.asmc.entity.commandType.Command_cmd;

public interface CommandTypeRegisterService {
	
	
	/**
	 * 向命令容器注册命令类型
	 */
	public boolean regCommandType(String typeName,Command_cmd commandType);


	/**
	 * 获取命令类型
	 */
	public Command_cmd getCommandType(String typeName) throws Exception;
	

}
