package com.ksptooi.ASMC.Config;

import com.ksptooi.ASMC.Entity.ConfigEntity;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;

public class ConfigManager {

	
	IOController_V5 v5=null;
	MessageManager msg=null;
	
	
	public ConfigManager(){
		v5=new IOController_V5();
		msg=new MessageManager();
	}
	
	
	public void ReadConfig(){
		
		ConfigEntity ce=new ConfigEntity();
		
		msg.sendSysMessage("·从文件读取配置项.");
		
		v5.setTarget(ASMC.getMainConfigFile());
		
		this.CheckAndCreatorConfig();
		
		ce.setActiveAccount(v5.getKeyValue("ActiveAccount"));
		
		ce.setSql_URL(v5.getKeyValue("sql_URL"));
		
		ce.setSql_USER(v5.getKeyValue("sql_USER"));
		
		ce.setSql_PWD(v5.getKeyValue("sql_PWD"));
		
		ce.setTable_Command(v5.getKeyValue("Table_Command"));
		
		ce.setField_CommandType(v5.getKeyValue("Field_CommandType"));
		
		ce.setField_CommandName(v5.getKeyValue("Field_CommandName"));
		
		ce.setField_CommandPath(v5.getKeyValue("Field_CommandPath"));
		
		ce.setFiled_CommandTitle(v5.getKeyValue("Filed_CommandTitle"));
		
		ce.setFiled_CommandCreateAccount(v5.getKeyValue("Filed_CommandCreateAccount"));

		ce.setTable_Account(v5.getKeyValue("Table_Account"));
		
		ce.setField_Account(v5.getKeyValue("Field_Account"));
		
		ce.setField_Password(v5.getKeyValue("Field_Password"));
		
		ce.setField_AccountType(v5.getKeyValue("Field_AccountType"));
		
		ce.setField_AccountTitle(v5.getKeyValue("Field_AccountTitle"));
		
		ce.setTable_Main(v5.getKeyValue("Table_Main"));
		
		ce.setField_AQSCPAccount(v5.getKeyValue("Field_AQSCPAccount"));
		
		ce.setField_AQSCPPassword(v5.getKeyValue("Field_AQSCPPassword"));
		
		ASMC.setConfigEntity(ce);
		
	}
	
	
	
	
	public void CheckAndCreatorConfig(){
		
		msg.sendSysMessage("·检查ASMC配置项");
		
		
		v5.setTarget(ASMC.getMainConfigFile());
		
		
		if(v5.createNewGdcFile(ASMC.getMainConfigFile())){
			
			msg.sendWarningMessage("·配置项不存在.");
			msg.sendWarningMessage("·创建默认配置项");
			
			v5.addLine("ActiveAccount=AQSCP");
			
			v5.addLine("sql_URL=jdbc:sqlserver://127.0.0.1:1433;DatabaseName=AQSCP");
			v5.addLine("sql_USER=sa");
			v5.addLine("sql_PWD=sa");

			
			v5.addLine("Table_Command=Commands");
			v5.addLine("Field_CommandType=Type");
			v5.addLine("Field_CommandName=Name");
			v5.addLine("Field_CommandPath=Path");
			v5.addLine("Filed_CommandTitle=Title");
			v5.addLine("Filed_CommandCreateAccount=CCA");
			
			v5.addLine("Table_Account=Account");
			v5.addLine("Field_Account=Name");
			v5.addLine("Field_Password=Password");
			v5.addLine("Field_AccountType=Type");
			v5.addLine("Field_AccountTitle=Title");
			
			v5.addLine("Table_Main=AQSCP");
			v5.addLine("Field_AQSCPAccount=AQSCPAccount");
			v5.addLine("Field_AQSCPPassword=AQSCPPassword");
			
			msg.sendWarningMessage("·ASMC配置项创建完成!");
			return;
		}
		
		
		msg.sendWarningMessage("·ASMC配置项检查完成!");
		
	}
	
	
	
}
