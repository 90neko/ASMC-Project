package com.ksptooi.asmc.message;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;
import org.fusesource.jansi.Ansi.Color;

import com.ksptooi.asmc.entity.plugins.LoadedPlugin;


public class NLogger implements Logger{
	
	
	
	
	private String info = null;
	private String success = null;
	private String warn = null;
	private String error = null;
	
	public NLogger() {
		

		this.info = "INFO";
		this.success = "SUCCESS";
		this.warn = "WARN";
		this.error = "ERROR";
		
	}
	
	public NLogger(LoadedPlugin lap) {
		
		
		this.info = lap.getName();
		this.success = lap.getName();
		this.warn = lap.getName();
		this.error = lap.getName();
		
	}
	
	
	
	/**
	 * 普通
	 */
	public void log(String str) {
		System.out.println(str);
	}
	
	
	/**
	 * 信息(不换行)
	 */
	public void ninfo(String str) {
		System.out.print(str);	
	}
	
	/**
	 * 换行符
	 */
	public void br() {
		System.out.println("");
	}

	
	/**
	 * 信息
	 */
	public void info(String str) {
		AnsiConsole.systemInstall();
		System.out.println("["+this.info+"]"+str);
		AnsiConsole.systemUninstall();
	}
	
	/**
	 * 成功
	 */
	public void success(String str) {
		AnsiConsole.systemInstall();
		System.out.println(ansi().fg(Color.GREEN).a("["+this.success+"]:"+str).reset());
		AnsiConsole.systemUninstall();
	}
	
	/**
	 * 警告
	 */
	public void warn(String str) {
		AnsiConsole.systemInstall();
		System.out.println(ansi().fg(Color.YELLOW).a("["+this.warn+"]:"+str).reset());
		AnsiConsole.systemUninstall();
	}
	
	/**
	 * 严重
	 */
	public void error(String str) {
		AnsiConsole.systemInstall();
		System.out.println(ansi().fg(Color.RED).a("["+this.error+"]:"+str).reset());
		AnsiConsole.systemUninstall();
	}
	
	/**
	 * 兼容代码
	 */

	@Override
	public void sendMessage(String Message) {
		this.log(Message);
	}

	@Override
	public void successMessage(String message) {
		this.success(message);
	}

	@Override
	public void sendSysMessage(String Message) {
		this.info(Message);
	}

	@Override
	public void sendWarningMessage(String Message) {
		this.warn(Message);
	}

	@Override
	public void sendErrorMessage(String Message) {
		this.error(Message);
	}

	@Override
	public void sendSysMessageNoLine(String Message) {
		this.ninfo(Message);
	}

	@Override
	public void sendBr() {
		this.br();
	}
	
	//获取用户输入的信息
	public String getMessage(){
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		
		String message=null;
		try {
			
			message = br.readLine();
			
			if(message.trim().equals("")){
				return "null";
			}
			
			return message;
			
		} catch (IOException e) {
			return "null";
		}
		
	}
	
	
	//获取用户输入的密码
	public String getMessageOfPWD(){
		
		Console cons=System.console();
		
		String str=String.valueOf(cons.readPassword("p:"));
		
		return str;
		
	}
	
	
	
	
}
