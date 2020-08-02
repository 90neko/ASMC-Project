package uk.iksp.asmc.data.inteface;

import java.util.ArrayList;

import uk.iksp.asmc.entity.command.CommandEntity;

public interface CommandData {

	
	/**
	 * 更新命令
	 */
	public void updateCommand(CommandEntity ce);
	
	
	/**
	 * 根据命令名查询命令
	 */
	public ArrayList<CommandEntity> queryCommandByName(String name);
	
	/**
	 * 查询所有命令
	 */
	public ArrayList<CommandEntity> queryCommand();
	
	
	/**
	 * 添加一个命令
	 */
	public void addCommand(CommandEntity ce);
	
	
	/**
	 * 删除命令
	 */
	public void delCommand(String cmd);
	
}
