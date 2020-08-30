package com.ksptooi.asmc.eventHandler;

import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.service.event.EventListener;

public class TestEventHandler2 implements EventListener{

	@Override
	public void onEvent(UserChangeEvent event) {

		
		
		
		System.out.println("用户切换事件!");
		
	}

	@Override
	public void onEvent(CommandEvent event) {
		
		System.out.println("命令事件!");
		
	}

	@Override
	public void onEvent(UnknowCommandEvent event) {
		// TODO Auto-generated method stub
		
	}

}
