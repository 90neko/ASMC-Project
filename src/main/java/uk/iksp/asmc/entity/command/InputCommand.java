package uk.iksp.asmc.entity.command;

import java.util.ArrayList;

public class InputCommand {

	
	//命令名称
	private String name=null;
	
	//命令参数
	private ArrayList<String> parameter=new ArrayList<String>();

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getParameter() {
		return parameter;
	}

	public void setParameter(ArrayList<String> parameter) {
		this.parameter = parameter;
		
		if(parameter == null){
			return;
		}
	}

	
}
