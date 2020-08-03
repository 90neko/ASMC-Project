package uk.iksp.asmc.entity.command;

import com.ksptooi.asmc.entity.commandType.Command_cmd;

import uk.iksp.asmc.command.exception.UnknowCommandTypeException;

public class CommandEntity {

	
	//命令id
	private int id = 0;
	//命令名称
	private String cm_Name=null;
	//命令类型
	private String cm_Type=null;
	//命令指向地址
	private String cm_Path=null;
	//命令指向文件
	private String cm_File=null;
	//命令描述
	private String cm_Title=null;
	//命令权限信息
	private String cm_CCA=null;
	//预输入命令
	private InputCommand inputCommand=null;
	
	
	public CommandEntity(){
		
	}
	
	public CommandEntity(InputCommand ic){
		this.inputCommand = ic;
	}
	
	
	//转换为Asmc命令
	public AsmcCommand getAsmcCommand(Command_cmd ct) throws UnknowCommandTypeException{
		
		if(ct == null){
			throw new UnknowCommandTypeException();
		}
		
		AsmcCommand ac = new AsmcCommand();
		
		ac.setId(this.id);
		ac.setCm_Name(this.cm_Name);
		ac.setCm_Type(this.cm_Type);
		ac.setCm_Path(this.cm_Path);
		ac.setCm_File(this.cm_File);
		ac.setCm_Title(this.cm_Title);
		ac.setCm_CCA(this.cm_CCA);
		ac.setCommandType(ct);
		
		return ac;
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



	public String getCm_Name() {
		return cm_Name;
	}



	public void setCm_Name(String cm_Name) {
		this.cm_Name = cm_Name;
	}

	public InputCommand getInputCommand() {
		return inputCommand;
	}

	public void setInputCommand(InputCommand inputCommand) {
		this.inputCommand = inputCommand;
	}

	
	
	
	
	
}
