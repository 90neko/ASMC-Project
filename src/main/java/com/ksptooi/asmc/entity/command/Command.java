package com.ksptooi.asmc.entity.command;

import com.ksptooi.asmc.entity.commandType.Command_cmd;

public class Command {
	
	//命令id
	private Integer id = null;
	
	//命令名称
	private String name = null;
	
	//命令类型
	private String type = null;
	
	//命令指向地址
	private String path = null;
	
	//命令指向文件
	private String file = null;
	
	//命令描述
	private String title = null;
	
	//命令权限信息
	private String cca = null;
	
	//执行类型
	private Command_cmd executeType = null;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCca() {
		return cca;
	}
	public void setCca(String cca) {
		this.cca = cca;
	}
	@Override
	public String toString() {
		return "Command [id=" + id + ", name=" + name + ", type=" + type + ", path=" + path + ", file=" + file
				+ ", title=" + title + ", cca=" + cca + "]";
	}
	public Command_cmd getExecuteType() {
		return executeType;
	}
	public void setExecuteType(Command_cmd executeType) {
		this.executeType = executeType;
	}
	
	

}
