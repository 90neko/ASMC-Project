package com.ksptooi.asmc.service.plugins;

import java.nio.file.Path;

import com.ksptooi.asmc.entity.plugins.ExternalPlugin;

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
	
	
	
	
	
	
	

}
