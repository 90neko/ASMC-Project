package com.ksptooi.ASMC.Command;

import java.util.ArrayList;

import com.ksptooi.ASMC.Entity.CommandEntity;

public class Command_ListCmd extends Command_cmd{

	@Override
	public void ExecuteOfType(CommandEntity ce1) {
		
		

		
		
		ArrayList<CommandEntity> list=cmm.getAllCommand();
		
		msg.sendBr();
		//��û��Ȩ�޵������ַ�����滻
		
		for(int i=0;i<list.size();i++){
			
			if(um.getActiveUser().equals(list.get(i).getCCA())){
				continue;				
			}

			list.get(i).setPath("******");
			
		}
		//���
		
		
		
		
		
		String R2="|   ����   |    Type    |             Path             |      Title      |  CCA��Ϣ  |";
		
		System.out.println(R2);
		
		String name="";
		String type="";
		String path="";
		String title="";
		String CCA="";		
		msg.sendBr();
		
		for(CommandEntity ce:list){
			
			name="|"+ce.getName();
			type=""+ce.getType();
			path=""+ce.getPath();
			title=""+ce.getTitle();
			CCA=""+ce.getCCA();
			
			
			while(name.length()<=9){
				name=name+" ";
			}
			name=name+"|";
			
			while(type.length()<=16){
				type=type+" ";
			}
			type=type+"|";
			
			while(path.length()<=30){
				path=path+" ";
			}
			path=path+"|";
			
			while(title.length()<=24){
				title=title+" ";
			}
			title=title+"|";
			
			while(CCA.length()<=9){
				CCA=CCA+" ";
			}
			CCA=CCA+"|";
			
			
			System.out.println(name+type+path+title+CCA);	
			
		}
		
		
	}
	
	
	

	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
}
