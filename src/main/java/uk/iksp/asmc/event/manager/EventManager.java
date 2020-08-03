package uk.iksp.asmc.event.manager;

import java.util.ArrayList;

import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

import uk.iksp.asmc.event.bus.EventBus;
import uk.iksp.asmc.plugins.type.ASMCPlugin;

public class EventManager {


	
	Logger log = Asmc.getLogger();
	
	
	//已注册的全部事件处理器
	private ArrayList<EventBus> pluginEventBus = null;
		

	public EventManager(){
		log.info("初始化内部组件 - 事件总线");
		
		this.pluginEventBus = new ArrayList<EventBus>();
	}
	
	
	
	//注册事件处理器
	public void regEventHandler(ASMCPlugin plugin,EventBus eveh) {
		
		log.info("注册事件处理器:"+plugin.getPluginName());
		
		String pluginName = Asmc.getCorePluginManager().getLoadedPlugin(plugin).getName();
		eveh.setPluginName(pluginName);
		
		//注册事件		
		pluginEventBus.add(eveh);
		
	}
	

	public ArrayList<EventBus> getPluginEventBus() {
		return pluginEventBus;
	}






	
	
	
}
