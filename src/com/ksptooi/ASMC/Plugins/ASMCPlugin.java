package com.ksptooi.ASMC.Plugins;

import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Entity.CommandEntity;

public class ASMCPlugin implements Command_cmd{

	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		
	}

	
	
	//返回插件自身
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	//插件加载时将会执行一次
	public void onEnable(){
		
	}
	
	
	
	
	
	
}
