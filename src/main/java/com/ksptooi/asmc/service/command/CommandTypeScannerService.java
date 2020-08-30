package com.ksptooi.asmc.service.command;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.commandType.Command_cmd;

public interface CommandTypeScannerService {

	
	/**
	 * 命令类型扫描器
	 */
	
	
	public Command_cmd getType(String TypeName);
	
	
	/**
	 * 获得命令执行类型
	 * 
	 * 当无法获取命令类型时将会返回null
	 */
	public Command getExecuteType(Command cmd);
	
	
	
	
	
	
	
}
