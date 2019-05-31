package com.ksptooi.ASMC.eventManager;

import java.util.HashMap;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Plugins.ASMCPlugin;
import com.ksptooi.ASMC.event.CommandEvent;

public class EventManager {

	
	private EventHandler eh=null;
	
	private HashMap<String,EventHandler> eventHandler =new HashMap<String,EventHandler>();
	
	public EventManager(){
		eh = new EventHandler();
	}
	
	
	public void startCommandEvent(CommandEvent ce){
		
		//执行自带的事件处理器
		CommandEvent event=eh.event_onCommand(ce);
		
		
		//执行插件的事件处理器
		for(String str:ASMC.getPluginManager().getRegCommandTypeList()){
			
			
			
			//通过插件命令类型获取插件主类
			ASMCPlugin asmcp=(ASMCPlugin)ASMC.getPluginManager().getInstallPlugin().get(str);
			
			//通过插件主类获取插件事件处理器
			EventHandler em=eventHandler.get(asmcp);
			
			
			if(em != null){
				
				System.out.println("执行插件事件:"+str);
				event=em.event_onCommand(ce);
				
				
			}
			
			
			
			
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


	
	//注册事件
	public void regEventHandler(ASMCPlugin plugin,EventHandler eveh) {
		
		//通过插件主类 找出命令类型
		String CommandType=ASMC.getPluginManager().getInstallCommandType().get(plugin);
		
		//注册事件
		
		eventHandler.put(CommandType, eveh);
		
	}



	
	
	
	
	
}
