package com.ksptooi.asmc.service.command;

import java.util.ArrayList;

import com.ksptooi.asmc.data.mapper.CommandMapper;
import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.main.Asmc;

public class CommandData implements CommandDataService{

	
	CommandMapper cmdMapper = null;
	
	
	public CommandData() {
		
		Asmc.getLogger().info("初始化内部组件 - CommandDataService");
		
		this.cmdMapper = Asmc.getContainerService().getBean(CommandMapper.class);
		
	}
	
	
	
	@Override
	public void insert(Command cmd) {
		this.cmdMapper.insert(cmd);
	}

	
	@Override
	public void delete(int cmd_id) {
		this.cmdMapper.delete(cmd_id);
	}

	
	@Override
	public void update(Command cmd) {
		this.cmdMapper.update(cmd);
	}

	
	@Override
	public ArrayList<Command> queryList(Command cmd) {	
		return this.cmdMapper.queryList(cmd);
	}

	
	@Override
	public Command query(Command cmd) {	
		return this.cmdMapper.query(cmd);
	}



	@Override
	public boolean isExist(Command cmd) {
			
		Command query = this.cmdMapper.query(cmd);
		
		
		if(query != null) {
			return true;
		}	
		
		return false;
	}



	@Override
	public boolean isExist(String name) {
		
		Command cmd = new Command();
		
		cmd.setName(name);
			
		return this.isExist(cmd);
	}

}
