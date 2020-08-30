package uk.iksp.asmc.plugin.loader;

import com.ksptooi.asmc.common.PluginClassLoader;
import com.ksptooi.asmc.entity.plugins.ExternalPlugin;
import com.ksptooi.asmc.entity.plugins.ExternalPluginFile;
import com.ksptooi.asmc.entity.plugins.LoadedPlugin;
import com.ksptooi.asmc.main.Asmc;

public class PluginLoader {

	
	
	public PluginLoader(){
		Asmc.getLogger().info("初始化内部组件 - ASMC插件加载器");
	}
	
	
	/**
	 * 加载插件
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public LoadedPlugin loadPlugin(ExternalPluginFile plugin) throws ClassNotFoundException, Exception{
		
		PluginClassLoader loader = null;
		
		LoadedPlugin lap = new LoadedPlugin();
		
		loader = new PluginClassLoader(plugin.getFile());
		
		
		lap.setAsmcPlugin((ExternalPlugin)loader.loadClass(plugin.getMainClass()));
		lap.setName(plugin.getName());
		lap.setFile(plugin.getFile());
		lap.setMainClass(plugin.getMainClass());
		
		//设置插件名
		lap.getAsmcPlugin().setPluginName(plugin.getName());
		
		return lap;
		
	}
	
	
	
}
