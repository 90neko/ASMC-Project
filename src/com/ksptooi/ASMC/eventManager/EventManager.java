package com.ksptooi.ASMC.eventManager;

import java.util.ArrayList;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Plugins.ASMCPlugin;
import com.ksptooi.ASMC.event.ActiveUserChangeEvent;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventManager {

	//自带的eventHandler
	private EventHandler eh=null;
	
	MessageManager msg = ASMC.getMessageManager();
	
	
	//已注册的全部事件
	private ArrayList<EventHandler> eventHandler =new ArrayList<EventHandler>();
	

	
	
	public EventManager(){
		eh = new EventHandler();
		
	}
	
	//开始一个Command事件
	public void startCommandEvent(CommandEvent ce){
		
		//执行自带的事件处理器
		CommandEvent event=eh.onCommand(ce);
		
		
		//执行插件的事件处理器
		for(EventHandler ch:eventHandler){		
			event = ch.onCommand(event);				
		}
		
		
		//判断是否被取消
		if(event.isCancel()){
			return;
		}
		
		//判断是否已被立即提交
		if(event.isCommit()){
			return;
		}
		
		
		//提交事件	
		event.getCommandType().ExecuteOfType(event.getCommandEntity());
		
	}

	
	
	//开始一个ActiveUserChange事件
	public boolean startActiveUserChangeEvent(ActiveUserChangeEvent event) {
		
		ActiveUserChangeEvent AUCE = eh.onActiveUserChange(event);
		
		for(EventHandler ch:eventHandler){		
			event = ch.onActiveUserChange(AUCE);				
		}
		
		if(event.isCancel()){
			return false;
		}
		
		//提交事件
		
		ASMC.getUserManager().changeActiveUser(AUCE.getChangeToUser());
		return true;
		
	}
	
	
	

	
	//注册事件
	public void regEventHandler(ASMCPlugin plugin,EventHandler eveh) {
		
		msg.sendSysMessage("注册事件处理器:"+plugin.getPluginName());
		
		//注册事件		
		eventHandler.add(eveh);
		
	}



	
	
	
	
	
}
