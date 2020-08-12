package com.ksptooi.asmc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.ksptooi.asmc.common.Project;
import com.ksptooi.asmc.common.StartPerformanceCount;
import com.ksptooi.asmc.entity.commandType.Cmd_List;
import com.ksptooi.asmc.entity.plugins.LoadedPlugin;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.message.NLogger;
import com.ksptooi.asmc.service.command.CommandData;
import com.ksptooi.asmc.service.command.CommandDataService;
import com.ksptooi.asmc.service.command.CommandParser;
import com.ksptooi.asmc.service.command.CommandParserService;
import com.ksptooi.asmc.service.command.CommandTypeRegister;
import com.ksptooi.asmc.service.command.CommandTypeRegisterService;
import com.ksptooi.asmc.service.command.CommandTypeScanner;
import com.ksptooi.asmc.service.command.CommandTypeScannerService;
import com.ksptooi.asmc.service.commandHandler.CommandHandler;
import com.ksptooi.asmc.service.commandHandler.CommandHandlerService;
import com.ksptooi.asmc.service.event.EventBus;
import com.ksptooi.asmc.service.event.EventBusService;
import com.ksptooi.asmc.service.plugins.CorePluginLoader;
import com.ksptooi.asmc.service.plugins.CorePluginLoaderService;
import com.ksptooi.asmc.service.plugins.CorePluginManager;
import com.ksptooi.asmc.service.plugins.CorePluginManagerService;
import com.ksptooi.asmc.service.spring.SpringContainer;
import com.ksptooi.asmc.service.spring.SpringContainerService;
import com.ksptooi.asmc.service.user.UserData;
import com.ksptooi.asmc.service.user.UserDataService;
import com.ksptooi.asmc.service.user.UserPermission;
import com.ksptooi.asmc.service.user.UserPermissionService;

import uk.iksp.v7.Factory.DataSessionFactory;
import uk.iksp.v7.FactoryBuilder.GeneralDataFactoryBuilder;


public class Asmc {
	
	
	//容器服务
	private static final SpringContainerService containerService = new SpringContainer();
	
	//事件总线服务
	private static final EventBusService eventBusService = new EventBus();
	
	//命令数据检索服务
	private static final CommandDataService commandDataService = new CommandData();

	//用户数据检索服务
	private static final UserDataService userDataService = new UserData();
	
	//用户权限操作服务
	private static final UserPermissionService userPermissionService = new UserPermission();
	
	//命令解析服务
	private static final CommandParserService commandParserService = new CommandParser();
	
	//命令执行类型扫描服务
	private static final CommandTypeScannerService commandTypeScannerService = new CommandTypeScanner();
	
	//命令类型注册服务
	private static final CommandTypeRegisterService commandTypeRegisterService = new CommandTypeRegister();
	
	//命令执行服务
	private static final CommandHandlerService commandHandlerService = new CommandHandler();
	
	//插件加载服务
	private static final CorePluginLoaderService corePluginLoaderService = new CorePluginLoader();
	
	//插件管理服务
	private static final CorePluginManagerService corePluginManagerService = new CorePluginManager();
	
	//日期格式化 SDF
	private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
	
	

	private static final BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	private static DataSessionFactory dataSessionFactory = new DataSessionFactory(4);
	
	private static GeneralDataFactoryBuilder generalDataFactoryBuilder=new GeneralDataFactoryBuilder();
	
	
	
	public static void main(String rk[]){
		
		Logger log=Asmc.getLogger();
		
		log.info("加载内部组件");
		
		StartPerformanceCount APC=new StartPerformanceCount();
		
		log.info("·Core 版本号:"+ Project.version);
		
		//开始性能计数
		APC.Timer();
		
		
		//初始化插件
//		corePluginManager = new CorePluginManager();
		
		
		corePluginManagerService.loadPluginForPath(Project.pluginFolder.toPath());	
		
		
		
		//加载ASMC插件
//		Asmc.getCorePluginManager().loadAllPlugin();
		
		
		//注册命令类型
		commandTypeRegisterService.regCommandType("cmd_list", new Cmd_List());
		
		

		//切换用户	
		Asmc.userPermissionService.setActiveUser(userDataService.getUser("TF801A"));
		
		log.br();
		log.success("init complete");
		log.success("at:"+APC.StopTimer()+" s");
		
		log.br();
		log.info("MODEL- - - - - - - - - ASMC_MultiPoint_Temernal(AMT)");
		log.info("OP Version - - - - - - AMT("+Project.version+")");
		log.info("Listen - - - - - - - - "+Project.listenerAddress);
		log.info("Date - - - - - - - - - "+ getDataFormat().format(new Date()));
		log.info("Status - - - - - - - - Running");
		
		
		commandHandlerService.commandHandler();
		
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

	


	public static GeneralDataFactoryBuilder getGeneralDataFactoryBuilder() {
		return generalDataFactoryBuilder;
	}



	public static void setGeneralDataFactoryBuilder(GeneralDataFactoryBuilder generalDataFactoryBuilder) {
		Asmc.generalDataFactoryBuilder = generalDataFactoryBuilder;
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
			ArrayList<LoadedPlugin> alp = Asmc.getCorePluginManagerService().getAllLoadedPlugin();
				
			for(LoadedPlugin lap:alp) {
				
				
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
	
	

	public static UserDataService getUserDataService() {
		return userDataService;
	}
	

	public static UserPermissionService getUserPermissionService() {
		return userPermissionService;
	}



	public static EventBusService getEventBusService() {
		return eventBusService;
	}





	public static CommandParserService getCommandParserService() {
		return commandParserService;
	}





	public static CommandTypeScannerService getCommandTypeScannerService() {
		return commandTypeScannerService;
	}





	public static CommandTypeRegisterService getCommandTypeRegisterService() {
		return commandTypeRegisterService;
	}






	public static CorePluginLoaderService getCorePluginLoaderService() {
		return corePluginLoaderService;
	}






	public static CorePluginManagerService getCorePluginManagerService() {
		return corePluginManagerService;
	}






	public static SimpleDateFormat getDataFormat() {
		return dataFormat;
	}



}
