package com.ksptooi.asmc.service.plugins;

import java.nio.file.Path;
import java.util.ArrayList;

import com.ksptooi.asmc.entity.plugins.ExternalPlugin;
import com.ksptooi.asmc.entity.plugins.LoadedPlugin;

public interface CorePluginManagerService {
	
	
	/**
	 * 加载路径下的插件包
	 * @param path 路径
	 */
	public void loadPluginForPath(Path path);
	
	
	/**
	 * 注册插件命令类型
	 */
	public void regPluginCommand(ExternalPlugin plugin,String commandTypeName,String commandTypePath);
	
	
	/**
	 * 获取已注册的插件列表
	 */
	public ArrayList<LoadedPlugin> getAllLoadedPlugin();
	
	/**
	 * 获取已注册插件
	 */
	public LoadedPlugin getPlugin(String pluginName);
	
	
}
