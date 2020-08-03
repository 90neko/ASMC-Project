package com.ksptooi.asmc.service.commandHandler;

import com.ksptooi.asmc.entity.command.Command;

public interface CommandTypeScannerService {

	
	/**
	 * 命令类型扫描器
	 */
	
	/**
	 * 获得命令执行类型
	 * 
	 * 当无法获取命令类型时将会返回null
	 */
	public Command getExecuteType(Command cmd);
	
	
	
	
}
