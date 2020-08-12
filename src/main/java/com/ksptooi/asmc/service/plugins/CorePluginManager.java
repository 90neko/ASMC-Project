package com.ksptooi.asmc.service.plugins;

import java.nio.file.Path;
import java.util.ArrayList;
import com.ksptooi.asmc.common.PluginClassLoader;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.entity.plugins.ExternalPlugin;
import com.ksptooi.asmc.entity.plugins.ExternalPluginFile;
import com.ksptooi.asmc.entity.plugins.LoadedPlugin;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandTypeRegisterService;

public class CorePluginManager implements CorePluginManagerService{

	private Logger log = Asmc.getLogger();
	
	//插件加载与搜寻服务
	CorePluginLoaderService pluginLoader = Asmc.getCorePluginLoaderService();
	
	//命令注册服务
	CommandTypeRegisterService cmdReg = Asmc.getCommandTypeRegisterService();
	
	
	//已注册的插件列表
	ArrayList<LoadedPlugin> loadedPlugin = new ArrayList<LoadedPlugin>();
	
	
	//已注册的命令类型Map (命令类型名 || 命令类型实例)
//	HashMap<String,Command_cmd> regCommandTypeMap=new HashMap<String,Command_cmd>();
	
	//已注册的命令列表
//	ArrayList<String> regCommandNameList=new ArrayList<String>();
	
	
	public CorePluginManager() {
		log.info("初始化内部组件 - CorePluginManager[CPM]");
	}
	
	
	
	/**
	 * 加载路径下的插件包
	 * @param path 路径
	 */
	@Override
	public void loadPluginForPath(Path path) {
		
		//搜索插件
		ArrayList<ExternalPluginFile> pluginList = this.pluginLoader.searchPlugins(path.toFile());
		
		//加载插件
		for(ExternalPluginFile plugin:pluginList){
			
			
//			try {
//				Class.forName(plugin.getMainClass());
//			} catch (ClassNotFoundException e) {
//				log.error("·CPM - 加载插件时发生错误! 无法载入插件主类:"+plugin.getName());
//				continue;
	//		}
			
			
			log.info("·CPM - 加载:"+plugin.getName());
			
			
			LoadedPlugin lp = null;
			
			lp = this.pluginLoader.loadPlugin(plugin);
			
			
			loadedPlugin.add(lp);
			
			//执行插件onEnable方法
			
			
		}	
		
		//执行插件的onEnable方法
		for(LoadedPlugin lap:this.loadedPlugin){
			lap.getAsmcPlugin().onEnable();
		}
		
	}
	

	/**
	 * 注册插件命令类型
	 */
	@Override
	public void regPluginCommand(ExternalPlugin plugin, String commandTypeName, String commandTypePath) {
		
		Command_cmd CE=null;
		
		LoadedPlugin lap = this.getPlugin(plugin.getPluginName());
		
		
		//判断是否冲突
		try {
			this.cmdReg.getCommandType(commandTypeName);
			log.error("注册命令失败,命令冲突.");
			return;
		}catch(Exception e) {
		}
		
		
		PluginClassLoader Loader = new PluginClassLoader(lap.getFile());
		
		Object obj = null;
		
		try {
			
			obj = Loader.loadClass(commandTypePath);
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("注册命令失败,发生异常!");
			return;
		}
		
		CE = (Command_cmd)obj;
		
		//注册命令
		
		this.cmdReg.regCommandType(commandTypeName, CE);	
//		log.info("注册命令"+commandTypeName);
		
	}

	
	/**
	 * 获取已注册的插件列表
	 */
	@Override
	public ArrayList<LoadedPlugin> getAllLoadedPlugin() {
		return this.loadedPlugin;
	}



	@Override
	public LoadedPlugin getPlugin(String pluginName) {
		
		
		for(LoadedPlugin lp:loadedPlugin) {
			if(lp.getName().equals(pluginName)) {
				return lp;
			}
		}
			
		return null;
	}
	
	
	
	

}
