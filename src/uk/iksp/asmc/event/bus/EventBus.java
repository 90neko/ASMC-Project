package uk.iksp.asmc.event.bus;

import uk.iksp.asmc.event.type.ActiveUserChangeEvent;
import uk.iksp.asmc.event.type.CommandEvent;
import uk.iksp.asmc.event.type.PreCommandEvent;
import uk.iksp.asmc.event.type.PreCommandInputEvent;
import uk.iksp.asmc.event.type.UnknowCommandEvent;

public class EventBus{

	
	private String pluginName ="Asmc_Core";
	
	//命令执行事件
	public CommandEvent onCommand(CommandEvent ce) {				
		return ce;		
	}
	
	//活动用户转换事件
	public ActiveUserChangeEvent onActiveUserChange(ActiveUserChangeEvent AUCE) {
		return AUCE;	
	}
	
	//预输入命令事件
	public PreCommandEvent onPreCommandEvent(PreCommandEvent PCE) {
		return PCE;
	}
	
	//未知命令事件
	public UnknowCommandEvent onUnknowCommandEvent(UnknowCommandEvent UCE){	
		return UCE;
	}
	
	//预命令输入事件
	public PreCommandInputEvent onPreCommandInputEvent(PreCommandInputEvent pcie){	
		return pcie;
	}
	
	

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	
	
	
}
