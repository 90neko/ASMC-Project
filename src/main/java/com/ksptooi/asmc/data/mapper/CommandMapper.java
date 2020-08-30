package com.ksptooi.asmc.data.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ksptooi.asmc.entity.command.Command;


public interface CommandMapper {


	/**
	 * 添加命令
	 */
	public void insert(Command cmd);

	/**
	 * 删除命令
	 */
	public void delete(@Param("id") int cmd_id);


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
	
}
