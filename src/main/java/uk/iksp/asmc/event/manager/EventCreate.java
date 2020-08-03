package uk.iksp.asmc.event.manager;

import java.util.ArrayList;

import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

import uk.iksp.asmc.entity.command.InputCommand;
import uk.iksp.asmc.event.bus.EventBus;
import uk.iksp.asmc.event.type.ActiveUserChangeEvent;
import uk.iksp.asmc.event.type.CommandEvent;
import uk.iksp.asmc.event.type.PreCommandEvent;
import uk.iksp.asmc.event.type.PreCommandInputEvent;
import uk.iksp.asmc.event.type.UnknowCommandEvent;

public class EventCreate {

	
	//Core的事件总线
	private EventBus eventBus=null;
	
	Logger log = Asmc.getLogger();
	
	
	private ArrayList<EventBus> pluginEventBus = null;
	
	public EventCreate(){
		this.pluginEventBus = Asmc.getEventmanager().getPluginEventBus();
		this.eventBus = new EventBus();
		
		log.info("初始化内部组件 - 事件创建器");
		
	}
	
	//开始一个预命令输入事件
	public void startPreCommandInput(){
		
		PreCommandInputEvent pcie = new PreCommandInputEvent();
		
		//执行自带的事件处理器
		PreCommandInputEvent event=eventBus.onPreCommandInputEvent(pcie);
		
		
		//执行插件的事件处理器
		for(EventBus ch:pluginEventBus){		
			event = ch.onPreCommandInputEvent(pcie);				
		}
		
		event.commit();
		
	}
	
	
	//开始一个Command事件
	public void startCommandEvent(CommandEvent ce){
		
		//执行自带的事件处理器
		CommandEvent event=eventBus.onCommand(ce);
		
		
		//执行插件的事件处理器
		for(EventBus ch:pluginEventBus){		
			event = ch.onCommand(event);				
		}
		
		event.commit();
		
	}

	
	//开始一个ActiveUserChange事件
	public boolean startActiveUserChangeEvent(ActiveUserChangeEvent auce) {
		
		ActiveUserChangeEvent event = eventBus.onActiveUserChange(auce);
		
		for(EventBus ch:pluginEventBus){		
			auce = ch.onActiveUserChange(event);				
		}
		
		if(event.isCancel()){
			return false;
		}
		
		event.commit();
		return true;
	}
	
	
	
	//开始一个PreCommandEvent事件
	public boolean startPreCommandEvent(InputCommand ic) {
		
		PreCommandEvent event = new PreCommandEvent(ic);
		
		for(EventBus ch:pluginEventBus){		
			event=ch.onPreCommandEvent(event);
		}
		
		if(event.isCancel()){
			return false;
		}
		
		event.commit();
		
		return true;
		
	}
	
	
	//开始一个UnknowCommandEvent事件
	public void startUnknowCommandEvent(InputCommand PreCommand){
		
		UnknowCommandEvent event=new UnknowCommandEvent(PreCommand);
		
		eventBus.onUnknowCommandEvent(event);
		
		//执行插件的事件处理器
		for(EventBus ch:pluginEventBus){		
			event = ch.onUnknowCommandEvent(event);
		}
		
		event.commit();
		
	}

	
}
