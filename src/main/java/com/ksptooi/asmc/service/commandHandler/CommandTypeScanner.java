package com.ksptooi.asmc.service.commandHandler;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;

public class CommandTypeScanner {

	
	//VF9 - 通过类名获取类实例
	
	//获取类型
	public static Command_cmd getType(String TypeName){
		
		try {

			
			Class<?> c=Class.forName("uk.iksp.asmc.command.type."+TypeName);

			Method m1 = c.getDeclaredMethod("getThis");  
			
			Object obj = c.newInstance();
			
			Command_cmd CT=(Command_cmd)m1.invoke(obj);
			
			return CT;
			
		} catch (Exception e) {	
			
			//获取插件的命令类型
			
			Command_cmd PCT=getTypeOfPlugin(TypeName);
			
			
			if(getTypeOfPlugin(TypeName) != null){
				return PCT;
			}
			
			Asmc.getLogger().warn("没有找到此CommandType:"+TypeName);
			return null;
		}
		
	}
	
	
	//从插件获取类型
	private static Command_cmd getTypeOfPlugin(String TypeName){
		
		
		HashMap<String, Command_cmd> pluginList=Asmc.getCorePluginManager().getRegCommandTypeMap();
		
			
		return pluginList.get(TypeName);
		
	}
	
	
	
}
