package com.ksptooi.asmc.service.event;

import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;

public interface EventBusService {

	
	/**
	 * 向事件总线注册一个事件
	 */
	public void regListener(EventListener listener);
	
	/**
	 * 向事件总线注册一个核心事件
	 */
	
	
	/**
	 * 推送事件
	 */
	public void event(UserChangeEvent userChangeEvent);
	
	public void event(CommandEvent commandEvent);
	
	public void event(UnknowCommandEvent commandEvent);

}
