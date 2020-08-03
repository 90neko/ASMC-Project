package com.ksptooi.asmc.service.commandHandler;

import uk.iksp.asmc.command.exception.CommandFormatException;
import uk.iksp.asmc.entity.command.InputCommand;

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
