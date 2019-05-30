package com.ksptooi.ASMC.Command;

import java.lang.reflect.Method;

import com.ksptooi.ASMC.Main.ASMC;

public class CommandTools {

	
	//VF9 - ͨ��������ȡ��ʵ��
	
	//��ȡ����
	public static Command_cmd getType(String TypeName){
		
		try {

			
			Class<?> c=Class.forName("com.ksptooi.ASMC.Command."+TypeName);

			Method m1 = c.getDeclaredMethod("getThis");  
			
			Object obj = c.newInstance();
			
			Command_cmd CT=(Command_cmd)m1.invoke(obj);
			
			return CT;
			
		} catch (Exception e) {	
			ASMC.getMessageManager().sendWarningMessage("û���ҵ���CommandType:"+TypeName);
			return null;
		}
		
	}
	
	
	
}
