package com.ksptooi.ASMC.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.ksptooi.ASMC.Config.ConfigManager;
import com.ksptooi.ASMC.Message.Logger;
import com.ksptooi.ASMC.Message.NLogger;
import com.ksptooi.ASMC.Util.ASMC_PerformanceCount;
import com.ksptooi.ASMC.Util.Var;

import uk.iksp.asmc.command.handler.CommandHandler;
import uk.iksp.asmc.entity.config.ConfigEntity;
import uk.iksp.asmc.entity.plugins.LoadedAsmcPlugin;
import uk.iksp.asmc.event.manager.EventCreate;
import uk.iksp.asmc.event.manager.EventManager;
import uk.iksp.asmc.plugins.manager.CorePluginManager;
import uk.iksp.asmc.services.CommandService;
import uk.iksp.asmc.services.MysqlServices;
import uk.iksp.asmc.services.UserService;
import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;


public class Asmc {
	
	
	private static ConfigEntity configEntity=null;
	
	private final static File pluginFolder=new File("C:/asmc_core/plugins/");
	
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
	
	public static final String asmc_Version = "V4.6-Y";
	
	public static void main(String rk[]) throws IOException, InterruptedException{
		
		Logger log=Asmc.getLogger();
		
		log.info("加载内部组件");
		
		ASMC_PerformanceCount APC=new ASMC_PerformanceCount();
		
		log.info("·Core 版本号:"+Asmc.asmc_Version);
		
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
		
		
		//切换用户	
		userService.changeActiveUser(userService.getUser("TF801A"));
		
		log.warn("启动完成");
		log.warn("ASMC启动耗时:"+APC.StopTimer());
		
		log.br();
		log.info("MODEL- - - - - - - - - ASMC_Single_Terminal(AST)");
		log.info("OP Program - - - - - - AST("+Asmc.asmc_Version+")");
		
		log.info("Status - - - - - - - - Terminal Stand By!");
		log.info("Listen - - - - - - - - 0.0.0.0:25567");
		log.info("Plugin - - - - - - - - Loaded ");
		log.info("Date(UTC)- - - - - - - "+Var.getUTCTimeStr());
		log.info("Status - - - - - - - - 终端准备完成.");
		
		ch.ExecuteCommand();
		
	}


	
	//getter setter
	public static ConfigEntity getConfigEntity() {
		return configEntity;
	}


	public static void setConfigEntity(ConfigEntity configEntity) {
		Asmc.configEntity = configEntity;
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
	
	
	/**
	 * 获取日志记录器
	 * @throws ClassNotFoundException 
	 */
	public static Logger getLogger() {
		
		//取上级方法堆栈
		String className=Thread.currentThread().getStackTrace()[2].getClassName();
		
		try {
			
			//取ALP
			ArrayList<LoadedAsmcPlugin> alp = Asmc.getCorePluginManager().getAllLoadedPlugin();
				
			for(LoadedAsmcPlugin lap:alp) {
				
				
				if(className.equalsIgnoreCase(lap.getMainClass().toString())) {
					return new NLogger(lap);
				}
				
			}
			
		}catch(Exception ea) {
			return new NLogger();
		}
		
		

		return new NLogger();
	}


	
}
