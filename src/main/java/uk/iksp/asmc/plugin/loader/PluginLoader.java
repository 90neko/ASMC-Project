package uk.iksp.asmc.plugin.loader;

import com.ksptooi.ASMC.Main.Asmc;

import uk.iksp.asmc.entity.plugins.AsmcPlugin;
import uk.iksp.asmc.entity.plugins.LoadedAsmcPlugin;
import uk.iksp.asmc.plugins.type.ASMCPlugin;

public class PluginLoader {

	
	
	public PluginLoader(){
		Asmc.getLogger().info("初始化内部组件 - ASMC插件加载器");
	}
	
	
	/**
	 * 加载插件
	 */
	public LoadedAsmcPlugin loadPlugin(AsmcPlugin plugin){
		
		PluginClassLoader loader = null;
		
		LoadedAsmcPlugin lap = new LoadedAsmcPlugin();
		
		loader = new PluginClassLoader(plugin.getFile());
		
		
		lap.setAsmcPlugin((ASMCPlugin)loader.loadClass(plugin.getMainClass()));
		lap.setName(plugin.getName());
		lap.setFile(plugin.getFile());
		lap.setMainClass(plugin.getMainClass());
		
		//设置插件名
		lap.getAsmcPlugin().setPluginName(plugin.getName());
		
		return lap;
		
	}
	
	
	
}
