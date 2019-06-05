package com.ksptooi.ASMC.Message;

import static org.fusesource.jansi.Ansi.ansi;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.fusesource.jansi.AnsiConsole;

public class MessageManager {

	
	PrintStream out = System.out;
	
	//发送消息(不含日志)
	public void sendMessage(String Message){
		out.println(Message);
	}
	
	
	//发送系统消息
	public void sendSysMessage(String Message){
		
		out.println("[信息]:"+Message);
		
	}
	
	//发送警告消息
	public void sendWarningMessage(String Message){
		
		AnsiConsole.systemInstall();
	
		out.println(ansi().fg(YELLOW).a("[警告]:"+Message).reset());
		
		AnsiConsole.systemUninstall();
		
		
//		System.out.println("[警告]:"+Message);
		
	}
	
	//发送错误消息
	public void sendErrorMessage(String Message){
		
		
		AnsiConsole.systemInstall();
		
		out.println(ansi().fg(RED).a("[严重]:"+Message).reset());
		
		AnsiConsole.systemUninstall();
		
//		System.out.println("[严重]:"+Message);
	}
	

	//发送系统信息(不换行)
	public void sendSysMessageNoLine(String Message){	
		out.print(Message);	
	}
	
	//发送换行符
	public void sendBr(){
		out.println("");
	}
	
	//获取用户输入的信息
	public String getMessage(){
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		String message=null;
		try {
			
			message = br.readLine();
			
			if(message.trim().equals("")){
				return null;
			}
			
			return message;
			
		} catch (IOException e) {
			return null;
		}
		
	}
	
	
	//获取用户输入的密码
	public String getMessageOfPWD(){
		
		Console cons=System.console();
		
		String str=String.valueOf(cons.readPassword("p:"));
		
		return str;
		
	}
	
	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}
	
	
}
