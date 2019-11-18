package uk.iksp.asmc.entity.command;

import com.ksptooi.ASMC.Main.ASMC;

public class CommandEntity {

	
	private int id = 0;
	private String cm_Name=null;
	private String cm_Type=null;
	private String cm_Path=null;
	private String cm_File=null;
	private String cm_Title=null;
	private String cm_CCA=null;
	private String PreCommand=null;


	
	
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




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getCm_Type() {
		return cm_Type;
	}




	public void setCm_Type(String cm_Type) {
		this.cm_Type = cm_Type;
	}




	public String getCm_Path() {
		return cm_Path;
	}




	public void setCm_Path(String cm_Path) {
		this.cm_Path = cm_Path;
	}




	public String getCm_File() {
		return cm_File;
	}




	public void setCm_File(String cm_File) {
		this.cm_File = cm_File;
	}




	public String getCm_Title() {
		return cm_Title;
	}




	public void setCm_Title(String cm_Title) {
		this.cm_Title = cm_Title;
	}




	public String getCm_CCA() {
		return cm_CCA;
	}




	public void setCm_CCA(String cm_CCA) {
		this.cm_CCA = cm_CCA;
	}




	public String getPreCommand() {
		return PreCommand;
	}




	public void setPreCommand(String preCommand) {
		PreCommand = preCommand;
	}




	public String getCm_Name() {
		return cm_Name;
	}




	public void setCm_Name(String cm_Name) {
		this.cm_Name = cm_Name;
	}
	
	
	
	
	
}
