package com.ksptooi.asmc.service.event;

import com.ksptooi.asmc.entity.event.CommandEvent;
import com.ksptooi.asmc.entity.event.UnknowCommandEvent;
import com.ksptooi.asmc.entity.event.UserChangeEvent;

public abstract interface EventListener {
	
	
	
	/**
	 * 用户切换事件
	 */
	public void onEvent(UserChangeEvent event);
	
	
	/**
	 * 命令事件
	 */
	public void onEvent(CommandEvent event);
	
	/**
	 * 未知命令事件
	 */
	public void onEvent(UnknowCommandEvent event);

}
