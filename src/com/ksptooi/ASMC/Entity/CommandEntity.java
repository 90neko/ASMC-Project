package com.ksptooi.ASMC.Entity;

import com.ksptooi.ASMC.Main.ASMC;

public class CommandEntity {

	private String PreCommand=null;
	
	private String Name=null;
	
	private String Type=null;
	
	private String Path=null;
	
	private String Title=null;
	
	private String CCA=null;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getCCA() {
		return CCA;
	}

	public void setCCA(String cCA) {
		CCA = cCA;
	}

	public String getPreCommand() {
		return PreCommand;
	}

	public void setPreCommand(String preCommand) {
		PreCommand = preCommand;
	}
	
	//获取命令参数
	public String[] getParameter(){
		
		String PerCommand = this.getPreCommand();
		String[] parameter = null;
		
		//非空判断
		if(PerCommand == null){
			return null;
		}
		
		try{
			//获取命令参数
			parameter = PerCommand.split(">")[1].split(",");
		}catch(Exception e){
			ASMC.getMessageManager().sendWarningMessage("此命令要求提供参数!");
			return null;
		}
		

		
		return parameter;	
		
	}
	
	
	
	
	
}
