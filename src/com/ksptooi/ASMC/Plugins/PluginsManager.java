package com.ksptooi.ASMC.Plugins;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import com.ksptooi.ASMC.Command.Command_cmd;
import com.ksptooi.ASMC.Main.ASMC;
import com.ksptooi.ASMC.Message.MessageManager;
import com.ksptooi.gdc.FileAPI.IOController_V5;
import sun.misc.ClassLoaderUtil;

public class PluginsManager {


	//已注册的插件列表
	HashMap<String,Command_cmd> installPlugin=new HashMap<String,Command_cmd>();
	
	
	//插件文件列表
	ArrayList<File> pluginList=new ArrayList<File>();
	
	//插件主类列表
	HashMap<String,String> pluginMainClassList=new HashMap<String,String>();
	
	//插件注册命令列表
	HashMap<String,String> pluginCommandTypeList=new HashMap<String,String>();
	
	//已注册的命令类型列表
	ArrayList<String> RegCommandTypeList=new ArrayList<String>();
	
	
	//公用
	IOController_V5 v5=ASMC.getV5();
	
	MessageManager msg = ASMC.getMessageManager();
	
	
	
	//加载插件
	public void LoadAllPlugins(){
		
		try{
			
			//加载
			for(File pluginFile:pluginList){
				
				//插件主类
				String pluginMainClass = pluginMainClassList.get(pluginFile.getName());
								
				//插件命令实例
				Command_cmd pluginCommandType=null;
				
				//插件文件名
				String pluginFileName=pluginFile.getName();
				
				//插件注册命令类型名
				String pluginRegCommandTypeName=pluginCommandTypeList.get(pluginFileName);
				
				
				URL url=pluginFile.toURI().toURL();
				ClassLoader loader=new URLClassLoader(new URL[]{url});//创建ClassLoader
				
				
				//判断命令类型是否已存在
				if(installPlugin.containsKey(pluginRegCommandTypeName)){
					
					msg.sendWarningMessage("插件"+pluginFileName+"注册命令类型时失败,命令类型出现冲突");
					
					ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
					continue;
				}
				
				
				msg.sendSysMessage("加载:"+pluginFile.getName());
				
				
				
				Class<?> cls=loader.loadClass(pluginMainClass);
				
				Method m1 = cls.getDeclaredMethod("getThis");
				
				Object obj = cls.newInstance();
				
				
				pluginCommandType=(Command_cmd)m1.invoke(obj);
						
				
				
				
				//注册插件
				installPlugin.put(pluginRegCommandTypeName, pluginCommandType);
				
				//添加插件命令类型到列表
				RegCommandTypeList.add(pluginRegCommandTypeName);
				
				
				//关闭ClassLoader
				ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
			}
			
			
			//显示已注册的命令类型
			for(String str:RegCommandTypeList){
				
				msg.sendSysMessage("注册命令类型:"+str);
				
			}
			
			
			
			//执行插件的onEnable
			for(String str:RegCommandTypeList){
				
				ASMCPlugin plugin=(ASMCPlugin) installPlugin.get(str);			
				plugin.onEnable();
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

		
		
	}
	
	
	
	
	
	
	//查找插件
	public void SearchPlugins(){

		
		msg.sendSysMessage("加载ASMC插件");
		
		
		//创建插件文件夹
		ASMC.getMainPluginsfolder().mkdirs();
		
		
		//取所有插件
		File[] PrePluginList = ASMC.getMainPluginsfolder().listFiles();
			
		
		//去除不是插件的文件 || 并将文件添加至 PluginList中
		for(int i=0;i<PrePluginList.length;i++){
			
			if( ! PrePluginList[i].getName().contains(".jar")){
				continue;
			}	
			
			pluginList.add(PrePluginList[i]);
			
		}

		try{		
	
			
			String commandType=null;
			
	
			//获取
			for(File f:pluginList){
				
				msg.sendSysMessage("获取:"+f.getName());
								
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd"); 
			
				
				InputStream is=url.openStream(); 		
				String main=v5.getKeyValueOfInputStream(is, "Plugin_Main");
				
				
				is=url.openStream();
				commandType=v5.getKeyValueOfInputStream(is, "Plugin_CommandTypeName");
							
				
				//加入插件主类
				pluginMainClassList.put(f.getName(), main);		
				
				//加入插件命令
				pluginCommandTypeList.put(f.getName(), commandType);
			
			}
			
				
			
		}catch(Exception e){		
			e.printStackTrace();			
		}
		

		
		
		
	}






	public HashMap<String, Command_cmd> getInstallPlugin() {
		return installPlugin;
	}










	public ArrayList<String> getRegCommandTypeList() {
		return RegCommandTypeList;
	}











	
	
}
