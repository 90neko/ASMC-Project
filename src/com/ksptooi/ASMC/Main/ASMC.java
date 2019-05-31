package com.ksptooi.ASMC.Main;

import java.io.File;
import java.io.IOException;
import com.ksptooi.ASMC.Config.ConfigManager;
import com.ksptooi.ASMC.Data.CommandManager;
import com.ksptooi.ASMC.Data.SqlManager;
import com.ksptooi.ASMC.Entity.ConfigEntity;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Plugins.PluginsManager;
import com.ksptooi.ASMC.Util.ASMC_PerformanceCount;
import com.ksptooi.ASMC.Util.Var;
import com.ksptooi.ASMC.auth.AuthManager;
import com.ksptooi.ASMC.eventManager.EventManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;


public class ASMC {


	private static ConfigEntity configEntity=null;
	
	private final static File mainConfigFile=new File("C:/ASMC/ASMC.Conf");
	
	private final static File mainPluginsFolder=new File("C:/ASMC/plugins/");
	
	private final static IOController_V5 v5=new IOController_V5();
	
	private final static SqlManager sqlManager=new SqlManager();
	
	private final static MessageManager messageManager=new MessageManager();
	
	private final static CommandHandler ch=new CommandHandler();
	
	private static CommandManager cm=null;
	
	private static AuthManager authManager=null;

	private static PluginsManager pluginManager=new PluginsManager();
	
	
	private final static EventManager eventManager=new EventManager();
	

	
	
	public static void main(String rk[]) throws IOException, InterruptedException{
		
		MessageManager msg=new MessageManager();
		
		ASMC_PerformanceCount APC=new ASMC_PerformanceCount();
		
		ConfigManager CM=new ConfigManager();
		
		SqlManager sqlManager=ASMC.getSqlmanager();
		 
		
		msg.sendSysMessage("ASMC �汾��:"+Var.ASMC_Version);
		
		//��ʼ���ܼ���
		APC.Timer();
		
		//�������ļ�
		CM.ReadConfig();		
		
		msg.sendWarningMessage("���ݿ�״̬:����");
		msg.sendSysMessage("�������������ݿ�");

		authManager = new AuthManager();
		
		sqlManager.Connect();
		
		if(!sqlManager.isActive()){
			
			msg.sendWarningMessage("���ݿ�״̬:����ʧ��");
			System.exit(0);
		}
		
		msg.sendSysMessage("�ȴ����ݿ�..");
		msg.sendWarningMessage("���ݿ�״̬:����");
		
		//����ASMC���
		ASMC.getPluginManager().SearchPlugins();
		
		//����
		ASMC.getPluginManager().LoadAllPlugins();
		
		
		msg.sendWarningMessage("ASMC������ʱ:"+APC.StopTimer());
		
		authManager.setActiveUser("user");
		
		cm=new CommandManager();
		
		ch.ExecuteCommand();
		
		
		
		
		
		
		
		
		
	}


	
	//getter setter
	public static ConfigEntity getConfigEntity() {
		return configEntity;
	}


	public static void setConfigEntity(ConfigEntity configEntity) {
		ASMC.configEntity = configEntity;
	}


	public static File getMainConfigFile() {
		return mainConfigFile;
	}



	public static SqlManager getSqlmanager() {
		return sqlManager;
	}



	public static MessageManager getMessageManager() {
		return messageManager;
	}



	public static CommandManager getCommandManager() {
		return cm;
	}



	public static AuthManager getUsermanager() {
		return authManager;
	}
	
	public static IOController_V5 getV5(){
		return v5;
	}



	public static File getMainPluginsfolder() {
		return mainPluginsFolder;
	}



	public static PluginsManager getPluginManager() {
		return pluginManager;
	}



	public static void setPluginManager(PluginsManager pluginManager) {
		ASMC.pluginManager = pluginManager;
	}




	public static EventManager getEventmanager() {
		return eventManager;
	}

	
}
