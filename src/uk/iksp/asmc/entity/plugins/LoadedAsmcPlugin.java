package uk.iksp.asmc.entity.plugins;

import java.util.ArrayList;

import com.ksptooi.ASMC.PluginsManager.ASMCPlugin;

public class LoadedAsmcPlugin extends AsmcPlugin{

	
	
	private ASMCPlugin asmcPlugin= null;
	
	private ArrayList<String> commands = new ArrayList<String>();
	
	
	public ASMCPlugin getAsmcPlugin() {
		return asmcPlugin;
	}

	public void setAsmcPlugin(ASMCPlugin asmcPlugin) {
		this.asmcPlugin = asmcPlugin;
	}

	public ArrayList<String> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<String> commands) {
		this.commands = commands;
	}
	
	
	
	
}
