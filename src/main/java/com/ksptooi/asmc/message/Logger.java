package com.ksptooi.asmc.message;

public interface Logger {

	
	public void log(String str);
	public void ninfo(String str);
	public void br();
	public void info(String str);
	public void success(String str);
	public void warn(String str);
	public void error(String str);
	
	
	public void sendMessage(String Message);
	public void successMessage(String message);
	public void sendSysMessage(String Message);
	public void sendWarningMessage(String Message);
	public void sendErrorMessage(String Message);
	public void sendSysMessageNoLine(String Message);
	public void sendBr();
	
	public String getMessage();
	public String getMessageOfPWD();
	
	
}
