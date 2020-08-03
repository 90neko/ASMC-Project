package com.ksptooi.asmc.service.event;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;
import com.ksptooi.asmc.main.Asmc;

public class EventBus implements EventBusService{

	
	public EventBus() {	
		Asmc.getLogger().info("初始化内部组件 - EventBus事件总线服务");		
	}
	
	private ArrayList<EventListener> core_listener = new ArrayList<EventListener>();
	
	private ArrayList<EventListener> plugin_Listener = new ArrayList<EventListener>();
	
	
	/**
	 * 注册事件
	 */
	@Override
	public void regListener(EventListener listener) {
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
		
		this.core_listener.forEach(E->{		
			E.onEvent(event);
		});
		
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
		
		this.core_listener.forEach(E->{		
			E.onEvent(event);
		});
		
	}


	@Override
	public void event(UnknowCommandEvent event) {
		
		for(EventListener el:plugin_Listener) {		
			el.onEvent(event);					
		}
		
		this.core_listener.forEach(E->{		
			E.onEvent(event);
		});
		
	}


	


	

	
	

}
