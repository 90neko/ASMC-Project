package com.ksptooi.asmc.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.ksptooi.asmc.entity.plugins.LoadedAsmcPlugin;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.message.NLogger;
import com.ksptooi.asmc.service.command.CommandData;
import com.ksptooi.asmc.service.command.CommandDataService;
import com.ksptooi.asmc.service.commandHandler.CommandHandler;
import com.ksptooi.asmc.service.spring.SpringContainer;
import com.ksptooi.asmc.service.spring.SpringContainerService;
import com.ksptooi.asmc.util.Var;
import com.ksptooi.asmc.util.ASMC_PerformanceCount;
import uk.iksp.asmc.event.manager.EventCreate;
import uk.iksp.asmc.event.manager.EventManager;
import uk.iksp.asmc.plugins.manager.CorePluginManager;
import uk.iksp.asmc.services.MysqlServices;
import uk.iksp.asmc.services.UserService;
import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;


public class Asmc {
	
	
	
	

	
	//容器服务
	private static final SpringContainerService containerService = new SpringContainer();
	
	//命令检索服务
	private static final CommandDataService commandDataService = new CommandData();
	
	private final static File pluginFolder=new File("C:/asmc_core/plugins/");
	
	private final static CommandHandler ch=new CommandHandler();

	private static final BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	private static EventManager eventManager=null;
	
	private static DataSessionFactory dataSessionFactory = new DataSessionFactory(4);
	
	private static GeneralDataFactoryBuilder generalDataFactoryBuilder=new GeneralDataFactoryBuilder();
	
	private static MysqlServices mysqlSerices = null;
	
	private static CorePluginManager corePluginManager = null;
	
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
		
		mysqlSerices = new MysqlServices();	
		
		//初始化插件
		corePluginManager = new CorePluginManager();
		
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
		log.info("Date(UTC)- - - - - - - "+ Var.getUTCTimeStr());
		log.info("Status - - - - - - - - 终端准备完成.");
		
		ch.commandHandler();
		
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







	public static SpringContainerService getContainerService() {
		return containerService;
	}







	public static CommandDataService getCommandDataService() {
		return commandDataService;
	}


	
}
