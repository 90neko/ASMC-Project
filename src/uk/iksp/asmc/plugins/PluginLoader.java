package uk.iksp.asmc.plugins;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;

import uk.iksp.asmc.plugins.PluginClassLoader;
import uk.iksp.asmc.plugins.type.ASMCPlugin;
import uk.iksp.asmc.entity.plugins.AsmcPlugin;
import uk.iksp.asmc.entity.plugins.LoadedAsmcPlugin;

public class PluginLoader{

	private MessageManager msg = ASMC.getMessageManager();
	
	/**
	 * 插件加载器
	 */
	public PluginLoader(){
		msg.sendSysMessage("初始化内部组件 - ASMC插件加载器");
	}
	
	/**
	 * 加载插件
	 */
	public LoadedAsmcPlugin loadPlugin(AsmcPlugin plugin){
		
		msg.sendSysMessage("·ASMC插件加载器 - 加载:"+plugin.getName());
		
		PluginClassLoader loader = new PluginClassLoader(plugin.getFile());
		
		
		LoadedAsmcPlugin lap=new LoadedAsmcPlugin();
		
		lap.setAsmcPlugin((ASMCPlugin)loader.loadClass(plugin.getMainClass()));
		lap.setName(plugin.getName());
		lap.setFile(plugin.getFile());
		lap.setMainClass(plugin.getMainClass());
		
		//设置插件名
		lap.getAsmcPlugin().setPluginName(plugin.getName());
		
		//执行插件的onEnable
		lap.getAsmcPlugin().onEnable();
		
		return lap;
	}
	
	
	
}
