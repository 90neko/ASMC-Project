package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import com.ksptooi.ASMC.Config.ConfigManager;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.ASMC.Util.ASMC_PerformanceCount;
import uk.iksp.asmc.command.handler.CommandHandler;
import uk.iksp.asmc.command.services.CommandService;
import uk.iksp.asmc.entity.config.ConfigEntity;
import uk.iksp.asmc.event.manager.EventCreate;
import uk.iksp.asmc.event.manager.EventManager;
import uk.iksp.asmc.mysql.MysqlServices;
import uk.iksp.asmc.plugins.manager.CorePluginManager;
import uk.iksp.asmc.user.service.UserService;
import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;


public class Asmc {
	
	
	private static ConfigEntity configEntity=null;
	
	private final static File pluginFolder=new File("C:/asmc_core/plugins/");
	
	private final static MessageManager messageManager=new MessageManager();
	
	private final static CommandHandler ch=new CommandHandler();

	private static final BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	private static EventManager eventManager=null;
	
	private static DataSessionFactory dataSessionFactory = new DataSessionFactory(4);
	
	private static GeneralDataFactoryBuilder generalDataFactoryBuilder=new GeneralDataFactoryBuilder();
	
	private static MysqlServices mysqlSerices = null;
	
	private static ConfigManager configManager = null;
	
	private static CorePluginManager corePluginManager = null;
	
	private static CommandService commandService = null;
	
	private static EventCreate eventCreate = null;
	
	private static UserService userService =null;
	
	public static final String asmc_Version = "V4.21-P";
	
	public static void main(String rk[]) throws IOException, InterruptedException{
		
		MessageManager msg=new MessageManager();
		
		msg.sendSysMessage("初始化内部组件");
		
		ASMC_PerformanceCount APC=new ASMC_PerformanceCount();
		
		msg.sendSysMessage("·Core 版本号:"+Asmc.asmc_Version);
		
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
		
		eventManager = new EventManager();
		
		eventCreate = new EventCreate();
		
		userService = new UserService();
		
		//加载ASMC插件
		Asmc.getCorePluginManager().loadAllPlugin();
		
		msg.sendWarningMessage("启动完成");
		msg.sendWarningMessage("ASMC启动耗时:"+APC.StopTimer());
		
		//切换用户	
		userService.changeActiveUser(userService.getUser("user"));
		
		ch.ExecuteCommand();
		
	}


	
	//getter setter
	public static ConfigEntity getConfigEntity() {
		return configEntity;
	}


	public static void setConfigEntity(ConfigEntity configEntity) {
		Asmc.configEntity = configEntity;
	}
	


	public static MessageManager getMessageManager() {
		return messageManager;
	}



	public static File getMainPluginsfolder() {
		return pluginFolder;
	}



	public static EventManager getEventmanager() {
		return eventManager;
	}

	
	public static BufferedReader getConsoleInput() {
		return br;
	}
	
	public static DataSessionFactory getDataSessionFactory() {
		return dataSessionFactory;
	}


	public static void setDataSessionFactory(DataSessionFactory dataSessionFactory) {
		Asmc.dataSessionFactory = dataSessionFactory;
	}


	public static MysqlServices getMysqlSerices() {
		return mysqlSerices;
	}



	public static void setMysqlSerices(MysqlServices mysqlSerices) {
		Asmc.mysqlSerices = mysqlSerices;
	}



	public static ConfigManager getConfigManager() {
		return configManager;
	}



	public static void setConfigManager(ConfigManager configManager) {
		Asmc.configManager = configManager;
	}



	public static CorePluginManager getCorePluginManager() {
		return corePluginManager;
	}



	public static void setCorePluginManager(CorePluginManager corePluginManager) {
		Asmc.corePluginManager = corePluginManager;
	}



	public static GeneralDataFactoryBuilder getGeneralDataFactoryBuilder() {
		return generalDataFactoryBuilder;
	}



	public static void setGeneralDataFactoryBuilder(GeneralDataFactoryBuilder generalDataFactoryBuilder) {
		Asmc.generalDataFactoryBuilder = generalDataFactoryBuilder;
	}



	public static CommandService getCommandService() {
		return commandService;
	}



	public static EventCreate getEventCreate() {
		return eventCreate;
	}



	public static void setEventCreate(EventCreate eventCreate) {
		Asmc.eventCreate = eventCreate;
	}



	public static UserService getUserService() {
		return userService;
	}



	public static void setUserService(UserService userService) {
		Asmc.userService = userService;
	}


	
}
