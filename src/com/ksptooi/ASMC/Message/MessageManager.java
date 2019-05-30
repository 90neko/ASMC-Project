package com.ksptooi.ASMC.Message;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import org.fusesource.jansi.AnsiConsole;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class MessageManager {

	
	
	//����ϵͳ��Ϣ
	public void sendSysMessage(String Message){
		
		
		System.out.println("[��Ϣ]:"+Message);
		
	}
	
	//���;�����Ϣ
	public void sendWarningMessage(String Message){
		
		AnsiConsole.systemInstall();
	
		System.out.println(ansi().fg(YELLOW).a("[����]:"+Message).reset());
		
		AnsiConsole.systemUninstall();
		
		
//		System.out.println("[����]:"+Message);
		
	}
	
	//���ʹ�����Ϣ
	public void sendErrorMessage(String Message){
		
		
		AnsiConsole.systemInstall();
		
		System.out.println(ansi().fg(RED).a("[����]:"+Message).reset());
		
		AnsiConsole.systemUninstall();
		
//		System.out.println("[����]:"+Message);
	}
	

	//����ϵͳ��Ϣ(������)
	public void sendSysMessageNoLine(String Message){	
		System.out.print(Message);	
	}
	
	//���ͻ��з�
	public void sendBr(){
		System.out.println("");
	}
	
	//��ȡ�û��������Ϣ
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
	
	
	//��ȡ�û����������
	public String getMessageOfPWD(){
		
		Console cons=System.console();
		
		String str=String.valueOf(cons.readPassword("p:"));
		
		return str;
		
	}
	
	
}
