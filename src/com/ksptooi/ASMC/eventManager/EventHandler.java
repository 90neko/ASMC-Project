package com.ksptooi.ASMC.eventManager;

import com.ksptooi.ASMC.event.ActiveUserChangeEvent;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventHandler{

	
	//����ִ���¼�
	public CommandEvent onCommand(CommandEvent ce) {
					
		return ce;		
	}

	
	
	//��û�ת���¼�
	public ActiveUserChangeEvent onActiveUserChange(ActiveUserChangeEvent AUCE) {
		return AUCE;	
	}
	
	
}
