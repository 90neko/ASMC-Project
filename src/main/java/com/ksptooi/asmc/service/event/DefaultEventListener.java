package com.ksptooi.asmc.service.event;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;


/**
 * 默认的事件处理器
 */
public class DefaultEventListener implements EventListener{

	public DefaultEventListener() {
		Asmc.getLogger().info("初始化内部组件 - DefaultEventListener");	
	}
	
	
	
	Logger logger = Asmc.getLogger();
	
	
	@Override
	public void onEvent(UserChangeEvent event) {
		logger.br();
		logger.success("Permissions has been updated");
		logger.success("new Active User for:"+event.getNewUser().getUserName());
	}

	
	@Override
	public void onEvent(CommandEvent event) {
		
		Command cmd = event.getCommand();
		
		if(event.getCommand().getType().equals("cmd_link")) {			
			logger.success("Vector:"+cmd.getType());
//			logger.info("Command Vector build complete");
			
		}
		
		
		cmd.getExecuteType().ExecuteOfType(cmd);
		

		
	}
	

	@Override
	public void onEvent(UnknowCommandEvent event) {
		logger.warn(event.getMessage());
	}

}
