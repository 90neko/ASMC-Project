package com.ksptooi.asmc.service.event;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.main.Asmc;

public class EventBus implements EventBusService{

	
	public EventBus() {	
		Asmc.getLogger().info("初始化内部组件 - EventBusService[EventBus]");		
	}
	
	//默认的事件处理
	private EventListener defaultEventHandler = new DefaultEventListener();
	
	
	private ArrayList<EventListener> plugin_Listener = new ArrayList<EventListener>();
	
	
	/**
	 * 注册事件
	 */
	@Override
	public void regListener(EventListener listener) {
		
		
		Asmc.getLogger().info("EventBus - 注册事件监听器:"+listener.getClass().toString());
		
		this.plugin_Listener.add(listener);
	}

	
	
	
	/**
	 * 推送事件
	 */
	@Override
	public void event(UserChangeEvent event) {

		for(EventListener el:plugin_Listener) {
			
			el.onEvent(event);
			
			//如果事件被取消则不继续往下传递
			if(event.isCancel()) {
				return;
			}
			
		}
		
		defaultEventHandler.onEvent(event);
		
	}


	@Override
	public void event(CommandEvent event) {
		
		for(EventListener el:plugin_Listener) {
			
			el.onEvent(event);
			
			//如果事件被取消则不继续往下传递
			if(event.isCancel()) {
				return;
			}
			
		}
		
		defaultEventHandler.onEvent(event);
		
	}


	@Override
	public void event(UnknowCommandEvent event) {
		
		for(EventListener el:plugin_Listener) {		
			el.onEvent(event);					
		}
		
		defaultEventHandler.onEvent(event);
		
	}


	


	

	
	

}
