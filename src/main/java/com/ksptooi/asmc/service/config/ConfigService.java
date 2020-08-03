package com.ksptooi.asmc.service.config;

import com.ksptooi.asmc.entity.config.Config;

public interface ConfigService {
	
	
	/**
	 * 读取配置文件
	 */
	public Config readConfig();
	
	
	/**
	 * 生成配置文件模板
	 */
	public void generalConfig();
	
	
	
	

}
