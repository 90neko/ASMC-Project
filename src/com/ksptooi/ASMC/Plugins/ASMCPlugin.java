package com.ksptooi.ASMC.Plugins;

public abstract class ASMCPlugin{


	private String PluginName="General ASMC Plugins";

	private boolean issetPluginName=false;
	
	
	//�������ʱ����ִ��һ��
	public void onEnable(){
		
		
	}
	
	
	public abstract ASMCPlugin getThis();
	
	
	public String getPluginName() {
		return PluginName;
	}

	public void setPluginName(String pluginName) {
		
		//�������ֻ������һ��
		if(issetPluginName == true) {
			return;
		}
		
		issetPluginName=true;	
		
		PluginName = pluginName;
	}
	
	
}
