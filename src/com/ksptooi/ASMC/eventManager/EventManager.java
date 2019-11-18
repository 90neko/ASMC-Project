package com.ksptooi.ASMC.eventManager;

import java.util.ArrayList;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.PluginsManager.ASMCPlugin;
import com.ksptooi.ASMC.event.ActiveUserChangeEvent;
import com.ksptooi.ASMC.event.CommandEvent;
import com.ksptooi.ASMC.event.PreCommandEvent;
import com.ksptooi.ASMC.event.UnknowCommandEvent;

public class EventManager {

	//自带的eventHandler
	private EventHandler basicEventHandler=null;
	
	MessageManager msg = ASMC.getMessageManager();
	
	//已注册的全部事件处理器
	private ArrayList<EventHandler> eventHandler =new ArrayList<EventHandler>();
		
	
	
	
	
	public EventManager(){
		basicEventHandler = new EventHandler();
		
	}
	
	//开始一个Command事件
	public void startCommandEvent(CommandEvent ce){
		
		//执行自带的事件处理器
		CommandEvent event=basicEventHandler.onCommand(ce);
		
		
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
		
		ActiveUserChangeEvent AUCE = basicEventHandler.onActiveUserChange(event);
		
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
	
	
	
	//开始一个PreCommandEvent事件
	public boolean startPreCommandEvent(String PreCommand) {
		
		PreCommandEvent PCE = new PreCommandEvent(PreCommand);
		
		for(EventHandler ch:eventHandler){		
			PCE=ch.onPreCommandEvent(PCE);
		}
		
		if(PCE.isCancel()){
			return false;
		}
		
		return true;
		
	}
	
	
	//开始一个UnknowCommandEvent事件
	public boolean startUnknowCommandEvent(String PreCommand){
		
		UnknowCommandEvent uce=new UnknowCommandEvent(PreCommand);
		
		basicEventHandler.onUnknowCommandEvent(uce);
		
		//执行插件的事件处理器
		for(EventHandler ch:eventHandler){		
			uce = ch.onUnknowCommandEvent(uce);
		}
		
		//判断是否取消
		if(uce.isCancel()){		
			return false;		
		}
		
		//判断是否立即提交
		if(uce.isCommit()){
			return false;
		}
		
		//发送一条错误消息
		ASMC.getMessageManager().sendWarningMessage("'"+uce.getPreCommand()+"'"+uce.getMessage());
		
		return true;
		
	}
	
	
	
	//注册事件处理器
	public void regEventHandler(ASMCPlugin plugin,EventHandler eveh) {
		
		msg.sendSysMessage("注册事件处理器:"+plugin.getPluginName());
		
		//注册事件		
		eventHandler.add(eveh);
		
	}



	
	
	
	
	
}
