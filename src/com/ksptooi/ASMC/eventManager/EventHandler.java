package com.ksptooi.ASMC.eventManager;

import com.ksptooi.ASMC.event.ActiveUserChangeEvent;
import com.ksptooi.ASMC.event.CommandEvent;
import com.ksptooi.ASMC.event.PreCommandEvent;

public class EventHandler{

	
	//命令执行事件
	public CommandEvent onCommand(CommandEvent ce) {
					
		return ce;		
	}

	
	
	//活动用户转换事件
	public ActiveUserChangeEvent onActiveUserChange(ActiveUserChangeEvent AUCE) {
		return AUCE;	
	}
	
	
	//预输入命令事件
	public PreCommandEvent onPreCommandEvent(PreCommandEvent PCE) {
		return PCE;
	}
	
	
}
