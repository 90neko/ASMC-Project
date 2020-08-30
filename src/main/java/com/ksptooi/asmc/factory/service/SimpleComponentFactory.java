package com.ksptooi.asmc.factory.service;

import com.ksptooi.asmc.service.command.*;
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

import java.text.SimpleDateFormat;

/**
 * 组件工厂
 */
@Deprecated
public interface SimpleComponentFactory {



/*    //事件总线
    public EventBusService eventBusService();

    //命令数据检索
    public CommandDataService commandDataService();

    //用户数据检索
    public UserDataService userDataService();

    //用户权限操作
    public UserPermissionService userPermissionService();

    //命令解析
    public CommandParserService commandParserService();

    //命令执行类型扫描
    public CommandTypeScannerService commandTypeScannerService();

    //命令类型注册
    public CommandTypeRegisterService commandTypeRegisterService();

    //命令执行
    public CommandHandlerService commandHandlerService  ();

    //插件加载
    public CorePluginLoaderService corePluginLoaderService  ();

    //插件管理
    public CorePluginManagerService corePluginManagerService  ();

    //日期格式化 SDF
    public SimpleDateFormat dataFormat  ();

    //容器
    public SpringContainerService containerService  ();

    //事件总线
    public EventBusService eventBusService  ();

    //命令数据检索
    public CommandDataService commandDataService  ();

    //用户数据检索
    public UserDataService userDataService  ();

    //用户权限操作
    public UserPermissionService userPermissionService() ;

    //命令解析
    public CommandParserService commandParserService();

    //命令执行类型扫描
    public CommandTypeScannerService commandTypeScannerService();*/


}
