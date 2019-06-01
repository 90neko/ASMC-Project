package com.ksptooi.ASMC.Plugins;

public abstract class ASMCPlugin{


	private String PluginName="General ASMC Plugins";

	private boolean issetPluginName=false;
	
	
	//插件加载时将会执行一次
	public void onEnable(){
		
		
	}
	
	
	public abstract ASMCPlugin getThis();
	
	
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
