package com.ksptooi.asmc.entity.plugins;

public abstract class ExternalPlugin{


	private String PluginName="General ASMC Plugins";

	
	private boolean issetPluginName=false;
	
	
	//插件加载时将会执行一次
	public void onEnable(){
		
	}
	
	
	public abstract ExternalPlugin getThis();
	
	
	public String getPluginName() {
		return PluginName;
	}

	public void setPluginName(String pluginName) {
		
		//插件名称只能设置一遍
		if(issetPluginName == true) {
			return;
		}
		
		issetPluginName=true;	
		
		PluginName = pluginName;
	}
	
	
}
