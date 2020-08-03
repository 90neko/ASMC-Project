package uk.iksp.asmc.plugins.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.entity.plugins.AsmcPlugin;
import com.ksptooi.asmc.entity.plugins.LoadedAsmcPlugin;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

import uk.iksp.asmc.plugin.loader.PluginClassLoader;
import uk.iksp.asmc.plugin.loader.PluginLoader;
import uk.iksp.asmc.plugin.loader.PluginSearch;
import uk.iksp.asmc.plugins.type.ASMCPlugin;

public class CorePluginManager{
	
	

	private Logger log = Asmc.getLogger();
	
	private final File pluginFolder=new File("C:/asmc_core/plugins/");
	
	
	//插件搜索器
	private PluginSearch pluginSearch = null;
	//插件加载器
	private PluginLoader pluginLoader = null;
	
	
	//已注册的插件列表
	ArrayList<LoadedAsmcPlugin> loadedPlugin = new ArrayList<LoadedAsmcPlugin>();
	
	
	//已注册的命令类型Map (命令类型名 || 命令类型实例)
	HashMap<String,Command_cmd> regCommandTypeMap=new HashMap<String,Command_cmd>();
	
	//已注册的命令列表
	ArrayList<String> regCommandNameList=new ArrayList<String>();
	
	
	
	/**
	 * 插件管理器
	 */
	public CorePluginManager(){
		
		log.info("初始化内部组件 - ASMC插件管理");
		
		this.pluginSearch = new PluginSearch();
		this.pluginLoader = new PluginLoader();
	}
	
	
	/**
	 * 加载所有ASMC插件
	 */
	public void loadAllPlugin(){
		
		
		
		//搜索插件
		ArrayList<AsmcPlugin> pluginList = this.pluginSearch.searchPlugins(pluginFolder);
		
		//加载插件
		for(AsmcPlugin plugin:pluginList){
			
			
			log.info("·ASMC插件加载器 - 加载:"+plugin.getName());
			
			LoadedAsmcPlugin lap = this.pluginLoader.loadPlugin(plugin);
			
			loadedPlugin.add(lap);
			
			//执行插件onEnable方法
			
			
		}	
		
		//执行插件的onEnable方法
		for(LoadedAsmcPlugin lap:this.loadedPlugin){
			lap.getAsmcPlugin().onEnable();
		}
		
		
		
	}
	
	/**
	 * 注册命令
	 */
	public void regCommandType(ASMCPlugin plugin,String commandName,String commandTypeEntityPath){
		
		Command_cmd CE=null;
		
		LoadedAsmcPlugin lap = this.getLoadedPlugin(plugin);
		
		//判断是否冲突
		for(String str:regCommandNameList) {
			
			if(str.equalsIgnoreCase(commandName)) {
				log.error("注册命令失败,命令冲突.");
				return;
			}
			
		}
		
		PluginClassLoader Loader = new PluginClassLoader(lap.getFile());
		
		Object obj=Loader.loadClass(commandTypeEntityPath);
		
		CE = (Command_cmd)obj;
		
		//注册命令
		lap.getCommands().add(commandName);
		this.regCommandNameList.add(commandName);
		this.regCommandTypeMap.put(commandName, CE);
		
		log.info("注册命令"+commandName);
		
	}
	
	
	/**
	 * 获取所有已加载插件
	 */
	public ArrayList<LoadedAsmcPlugin> getAllLoadedPlugin(){	
		return this.loadedPlugin;
	}
	
	
	
	/**
	 * 获取已加载的插件 根据名称
	 */
	public LoadedAsmcPlugin getLoadedPlugin(String name){
		
		
		for(LoadedAsmcPlugin lap:loadedPlugin){
			
			if(lap.getName().equals(name)){
				return lap;
			}
			
		}
		
		return null;
		
	}
	
	/**
	 * 获取已加载的插件 根据插件主类
	 */
	public LoadedAsmcPlugin getLoadedPlugin(ASMCPlugin plugin){
		return getLoadedPlugin(plugin.getPluginName());	
	}
	
	
	
	
	


	public File getPluginfolder() {
		return pluginFolder;
	}



	public PluginSearch getPluginSearch() {
		return pluginSearch;
	}



	public void setPluginSearch(PluginSearch pluginSearch) {
		this.pluginSearch = pluginSearch;
	}


	public HashMap<String, Command_cmd> getRegCommandTypeMap() {
		return regCommandTypeMap;
	}


	public void setRegCommandTypeMap(HashMap<String, Command_cmd> regCommandTypeMap) {
		this.regCommandTypeMap = regCommandTypeMap;
	}
	
	
	
}
