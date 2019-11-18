package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ksptooi.ASMC.Config.ConfigManager;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.PluginsManager.PluginsManager;
import com.ksptooi.ASMC.Util.ASMC_PerformanceCount;
import com.ksptooi.ASMC.auth.AuthManager;
import com.ksptooi.ASMC.eventManager.EventManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;
import uk.iksp.asmc.Mysql.MysqlServices;
import uk.iksp.asmc.plugins.CorePluginManager;
import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;
import uk.iksp.asmc.command.services.CommandService;
import uk.iksp.asmc.entity.config.ConfigEntity;


public class ASMC {
	
	
	private static ConfigEntity configEntity=null;
	
	
	private final static File pluginFolder=new File("C:/asmc_core/plugins/");
	
	private final static IOController_V5 v5=new IOController_V5();
	
	
	private final static MessageManager messageManager=new MessageManager();
	
	private final static CommandHandler ch=new CommandHandler();
	
	private static AuthManager authManager=null;

	private static PluginsManager pluginManager=new PluginsManager();
	
	private static final BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	private final static EventManager eventManager=new EventManager();
	
	
	private static DataSessionFactory dataSessionFactory = new DataSessionFactory(4);
	
	private static GeneralDataFactoryBuilder generalDataFactoryBuilder=new GeneralDataFactoryBuilder();
	
	private static MysqlServices mysqlSerices = null;
	
	private static ConfigManager configManager = null;
	
	private static CorePluginManager corePluginManager = null;
	
	private static CommandService commandService = null;
	
	public static final String ASMC_Version = "V4.05-E";
	
	
	public static void main(String rk[]) throws IOException, InterruptedException{
		
		
		MessageManager msg=new MessageManager();
		
		
		msg.sendSysMessage("初始化内部组件");
		
		ASMC_PerformanceCount APC=new ASMC_PerformanceCount();
		
		
		
		
		msg.sendSysMessage("·Core 版本号:"+ASMC.ASMC_Version);
		
		//开始性能计数
		APC.Timer();
		
		configManager = new ConfigManager();
		
		
		//读配置文件
		configManager.ReadConfig();		
		
		mysqlSerices = new MysqlServices();
		
		
		//初始化插件
		corePluginManager = new CorePluginManager();
		
		//初始化命令服务
		commandService = new CommandService();
		
		
		//查找ASMC插件
		ASMC.getPluginManager().SearchPlugins();
		
		//加载
		ASMC.getPluginManager().LoadAllPlugins();
		
		msg.sendWarningMessage("启动完成");
		msg.sendWarningMessage("ASMC启动耗时:"+APC.StopTimer());
		
//		authManager.setActiveUser(ASMC.getUserManager().getUser("user"));
		
		
		ch.ExecuteCommand();
		
	}


	
	//getter setter
	public static ConfigEntity getConfigEntity() {
		return configEntity;
	}


	public static void setConfigEntity(ConfigEntity configEntity) {
		ASMC.configEntity = configEntity;
	}
	


	public static MessageManager getMessageManager() {
		return messageManager;
	}





	public static AuthManager getUserManager() {
		return authManager;
	}
	
	public static IOController_V5 getV5(){
		return v5;
	}



	public static File getMainPluginsfolder() {
		return pluginFolder;
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

	
	public static BufferedReader getBr() {
		return br;
	}






	public static DataSessionFactory getDataSessionFactory() {
		return dataSessionFactory;
	}



	public static void setDataSessionFactory(DataSessionFactory dataSessionFactory) {
		ASMC.dataSessionFactory = dataSessionFactory;
	}



	public static MysqlServices getMysqlSerices() {
		return mysqlSerices;
	}



	public static void setMysqlSerices(MysqlServices mysqlSerices) {
		ASMC.mysqlSerices = mysqlSerices;
	}



	public static ConfigManager getConfigManager() {
		return configManager;
	}



	public static void setConfigManager(ConfigManager configManager) {
		ASMC.configManager = configManager;
	}



	public static CorePluginManager getCorePluginManager() {
		return corePluginManager;
	}



	public static void setCorePluginManager(CorePluginManager corePluginManager) {
		ASMC.corePluginManager = corePluginManager;
	}



	public static GeneralDataFactoryBuilder getGeneralDataFactoryBuilder() {
		return generalDataFactoryBuilder;
	}



	public static void setGeneralDataFactoryBuilder(GeneralDataFactoryBuilder generalDataFactoryBuilder) {
		ASMC.generalDataFactoryBuilder = generalDataFactoryBuilder;
	}



	public static CommandService getCommandService() {
		return commandService;
	}



	public static void setCommandService(CommandService commandService) {
		commandService = commandService;
	}


	
}
