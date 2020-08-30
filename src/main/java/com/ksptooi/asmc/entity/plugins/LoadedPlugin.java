package com.ksptooi.asmc.entity.plugins;

import java.util.ArrayList;

public class LoadedPlugin extends ExternalPluginFile{

	
	
	private ExternalPlugin asmcPlugin= null;
	
	private ArrayList<String> commands = new ArrayList<String>();
	
	
	public ExternalPlugin getAsmcPlugin() {
		return asmcPlugin;
	}

	public void setAsmcPlugin(ExternalPlugin asmcPlugin) {
		this.asmcPlugin = asmcPlugin;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}
	
	
	
	
}
