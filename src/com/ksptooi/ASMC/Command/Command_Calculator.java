package com.ksptooi.ASMC.Command;

import com.ksptooi.ASMC.Entity.CommandEntity;
import com.ksptooi.ASMC.Message.MessageManager;

public class Command_Calculator implements Command_cmd{

	
	private MessageManager msg=new MessageManager();
	
	@Override
	public void ExecuteOfType(CommandEntity ce) {
		
		
		String preCommand = ce.getPreCommand();
		String perameter = null;
		
		double result=0;
		
		try{
			
			perameter = preCommand.split(">")[1];
			
			
			//加
			if(perameter.contains("+")){
				
				String[] st=perameter.split("\\+");
				
				
				for(String str:st){
					
					result = result + Double.valueOf(str);
					
				}
				
				System.out.println("结果集:"+result);
				
				
			}
			
			//减
			if(perameter.contains("-")){
				
				String[] st=perameter.split("\\-");
				
				result = Double.valueOf(st[0]);
				
				for(int i=1;i<st.length;i++){
					
					result = result - Double.valueOf(st[i]);
					
				}
				
				System.out.println("结果集:"+result);
				
			}
			
			
			//乘
			if(perameter.contains("*")){
				
				String[] st=perameter.split("\\*");
				
				result = Double.valueOf(st[0]);
				
				for(int i=1;i<st.length;i++){
					
					result = result * Double.valueOf(st[i]);
					
				}
				
				System.out.println("结果集:"+result);
				
			}
			
			//除
			if(perameter.contains("/")){
				
				String[] st=perameter.split("\\/");
				
				result = Double.valueOf(st[0]);
				
				for(int i=1;i<st.length;i++){
					
					result = result / Double.valueOf(st[i]);
					
				}
				
				System.out.println("结果集:"+result);
				
			}
			
			
			
			
		}catch(Exception e){
			msg.sendWarningMessage("参数错误");
			return;
		}
		
		
		
	
		
		
		
	}

	
	
	
	
	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
