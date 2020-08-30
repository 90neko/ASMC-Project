package com.ksptooi.asmc.service.command;

import com.ksptooi.asmc.entity.command.InputCommand;

import uk.iksp.asmc.command.exception.CommandFormatException;

public interface CommandParserService {

	/**
	 * 命令解析器
	 */
	public String format(String preCommand) throws CommandFormatException;
	
	/**
	 * 进行命令解析
	 */
	public InputCommand parseAsInputCommand(String command);
	
	
	
	
	
	
}
