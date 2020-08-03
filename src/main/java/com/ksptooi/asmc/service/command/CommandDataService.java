package com.ksptooi.asmc.service.command;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.Command;

public interface CommandDataService {

	
	/**
	 * 添加命令
	 */
	public void insert(Command cmd);
	
	/**
	 * 删除命令
	 */
	public void delete(int cmd_id);
	
	
	/**
	 * 修改命令
	 */
	public void update(Command cmd);
	
	/**
	 * 查询命令(返回list)
	 */
	public ArrayList<Command> queryList(Command cmd);
	
	
	/**
	 * 查询命令(返回单条命令)
	 */
	public Command query(Command cmd);
	
	/**
	 * 判断命令是否存在
	 */
	public boolean isExist(Command cmd); 
	
	/**
	 * 判断命令是否存在
	 */
	public boolean isExist(String name); 
	
	
}
