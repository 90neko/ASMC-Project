package com.ksptooi.asmc.service.plugins;

import java.io.File;
import java.util.ArrayList;

import com.ksptooi.asmc.entity.plugins.ExternalPluginFile;
import com.ksptooi.asmc.entity.plugins.LoadedPlugin;

public interface CorePluginLoaderService {

	
	
	/**
	 * 搜索并读取目录下的插件文件
	 * @param folder
	 * @return
	 */
	public ArrayList<ExternalPluginFile> searchPlugins(File folder);
	
	
	
	/**
	 * 加载插件
	 */
	public LoadedPlugin loadPlugin(ExternalPluginFile plugin);
	
	
}
