package com.ksptooi.ASMC.Config;

import java.io.File;

import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import uk.iksp.asmc.entity.config.ConfigEntity;
import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.Session.DataSession;

public class ConfigManager {

	private final File configFile=new File("C:/asmc_core/asmc.conf");
	
	DataSessionFactory dataSessionFactory = null;
	
	MessageManager msg=null;
	
	
	
	public ConfigManager(){

		this.msg=ASMC.getMessageManager();
		this.dataSessionFactory=ASMC.getDataSessionFactory();
		
		msg.sendSysMessage("初始化内部组件 - 配置管理");
		
	}
	
	
	public void ReadConfig(){
		
		ConfigEntity ce=new ConfigEntity();
		
		
		msg.sendSysMessage("·配置管理 - 读取配置项");
		
		
		this.createConfig();

		
		try(DataSession ds=dataSessionFactory.openSession(configFile)){
					
			ce.setUrl(ds.get("url"));
			ce.setUserName(ds.get("userName"));
			ce.setPassWord(ds.get("passWord"));
			ce.setPoolMaximumActiveConnections(ds.get("poolMaximumActiveConnections"));
				
		}
		
		
		ASMC.setConfigEntity(ce);
		
	}
	
	
	
	
	public void createConfig(){
		
		msg.sendSysMessage("·配置管理 - 检查配置项.");
		
		
		
		boolean isCreate=dataSessionFactory.createdata(configFile);
		
		
		if(isCreate){
			
			msg.sendSysMessage("·配置管理 - 创建默认配置项.");
			msg.sendSysMessage("·配置管理 - 写入配置项");
			
	
			try(DataSession ds=dataSessionFactory.openSession(configFile)){
						
				
				ds.put("url", "jdbc:mysql://127.0.0.1:3306/");
				ds.put("userName", "root");
				ds.put("passWord", "root");
				ds.put("poolMaximumActiveConnections", "8");
				
			}
			
			
		}
		
		
		msg.sendSysMessage("·配置管理 - 完成检查");
		
	}


	public File getConfigFile() {
		return configFile;
	}
	
	
	
}
