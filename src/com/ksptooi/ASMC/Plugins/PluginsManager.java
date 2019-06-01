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


	//��ע��Ĳ���б�
	HashMap<String,Command_cmd> installPlugin=new HashMap<String,Command_cmd>();
	
	
	//����ļ��б�
	ArrayList<File> pluginList=new ArrayList<File>();
	
	//��������б�
	HashMap<String,String> pluginMainClassList=new HashMap<String,String>();
	
	//���ע�������б�
	HashMap<String,String> pluginCommandTypeList=new HashMap<String,String>();
	
	//��ע������������б�
	ArrayList<String> RegCommandTypeList=new ArrayList<String>();
	
	
	//����
	IOController_V5 v5=ASMC.getV5();
	
	MessageManager msg = ASMC.getMessageManager();
	
	
	
	//���ز��
	public void LoadAllPlugins(){
		
		try{
			
			//����
			for(File pluginFile:pluginList){
				
				//�������
				String pluginMainClass = pluginMainClassList.get(pluginFile.getName());
								
				//�������ʵ��
				Command_cmd pluginCommandType=null;
				
				//����ļ���
				String pluginFileName=pluginFile.getName();
				
				//���ע������������
				String pluginRegCommandTypeName=pluginCommandTypeList.get(pluginFileName);
				
				
				URL url=pluginFile.toURI().toURL();
				ClassLoader loader=new URLClassLoader(new URL[]{url});//����ClassLoader
				
				
				//�ж����������Ƿ��Ѵ���
				if(installPlugin.containsKey(pluginRegCommandTypeName)){
					
					msg.sendWarningMessage("���"+pluginFileName+"ע����������ʱʧ��,�������ͳ��ֳ�ͻ");
					
					ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
					continue;
				}
				
				
				msg.sendSysMessage("����:"+pluginFile.getName());
				
				
				
				Class<?> cls=loader.loadClass(pluginMainClass);
				
				Method m1 = cls.getDeclaredMethod("getThis");
				
				Object obj = cls.newInstance();
				
				
				pluginCommandType=(Command_cmd)m1.invoke(obj);
						
				
				
				
				//ע����
				installPlugin.put(pluginRegCommandTypeName, pluginCommandType);
				
				//��Ӳ���������͵��б�
				RegCommandTypeList.add(pluginRegCommandTypeName);
				
				
				//�ر�ClassLoader
				ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
			}
			
			
			//��ʾ��ע�����������
			for(String str:RegCommandTypeList){
				
				msg.sendSysMessage("ע����������:"+str);
				
			}
			
			
			
			//ִ�в����onEnable
			for(String str:RegCommandTypeList){
				
				ASMCPlugin plugin=(ASMCPlugin) installPlugin.get(str);			
				plugin.onEnable();
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

		
		
	}
	
	
	
	
	
	
	//���Ҳ��
	public void SearchPlugins(){

		
		msg.sendSysMessage("����ASMC���");
		
		
		//��������ļ���
		ASMC.getMainPluginsfolder().mkdirs();
		
		
		//ȡ���в��
		File[] PrePluginList = ASMC.getMainPluginsfolder().listFiles();
			
		
		//ȥ�����ǲ�����ļ� || �����ļ������ PluginList��
		for(int i=0;i<PrePluginList.length;i++){
			
			if( ! PrePluginList[i].getName().contains(".jar")){
				continue;
			}	
			
			pluginList.add(PrePluginList[i]);
			
		}

		try{		
	
			
			String commandType=null;
			
	
			//��ȡ
			for(File f:pluginList){
				
				msg.sendSysMessage("��ȡ:"+f.getName());
								
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd"); 
			
				
				InputStream is=url.openStream(); 		
				String main=v5.getKeyValueOfInputStream(is, "Plugin_Main");
				
				
				is=url.openStream();
				commandType=v5.getKeyValueOfInputStream(is, "Plugin_CommandTypeName");
							
				
				//����������
				pluginMainClassList.put(f.getName(), main);		
				
				//����������
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
