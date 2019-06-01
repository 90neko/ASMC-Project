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


	//��ע��Ĳ������Map (����� || �������ʵ��)
	HashMap<String,ASMCPlugin> installPluginMainClassMap=new HashMap<String,ASMCPlugin>();
	
	//��ע�����б�
	ArrayList<String> installPluginList = new ArrayList<String>();

	
	
	
	
	//��ע��������Map (����� || �����ַ)
	HashMap<String,String> pluginMainClassMap=new HashMap<String,String>();
	
	//��ע�����ļ�map (����� || �ļ�ʵ��)
	HashMap<String,File> pluginFileMap=new HashMap<String,File>();	
	
	//��ע�����ļ��б�
	ArrayList<File> pluginList=new ArrayList<File>();
	
	//��ע���������б�
	ArrayList<String> pluginNameList=new ArrayList<String>();
	
	
	
	//��ע�����������Map (���������� || ��������ʵ��)
	HashMap<String,Command_cmd> regCommandTypeMap=new HashMap<String,Command_cmd>();

	//��ע��������б�
	ArrayList<String> regCommandNameList=new ArrayList<String>();
	
	
	
	
	
	//����
	IOController_V5 v5=ASMC.getV5();
	
	MessageManager msg = ASMC.getMessageManager();
	

	
	public void regCommandType(String CommandTypeName,Command_cmd CommandTypeEntity) {
		
			
		
		//�ж��Ƿ��ͻ
		for(String str:regCommandNameList) {
			
			if(str.equalsIgnoreCase(CommandTypeName)) {
				msg.sendErrorMessage("ע������ʧ��,�����ͻ.");
				return;
			}
			
		}
			

			
		msg.sendSysMessage("ע������:"+CommandTypeName);
			
			
		
		//ע������
		regCommandNameList.add(CommandTypeName);
		
		regCommandTypeMap.put(CommandTypeName, CommandTypeEntity);
		
	}
	

	/**
	 * 
	
	//ע������
	public void regCommandType(ASMCPlugin plugin,String CommandTypeName,String CommandTypeEntityAddress) {
		
		
		Command_cmd CE=null;
		
		File pluginFile=pluginFileMap.get(plugin.getPluginName());
		
		//�ж��Ƿ��ͻ
		for(String str:regCommandNameList) {
			
			if(str.equalsIgnoreCase(CommandTypeName)) {
				msg.sendErrorMessage("ע������ʧ��,�����ͻ.");
				return;
			}
			
		}
			
		
		try {
			
			URL url=pluginFile.toURI().toURL();
			ClassLoader loader=new URLClassLoader(new URL[]{url});//����ClassLoader
							
			msg.sendSysMessage("ע������:"+CommandTypeName);
			
			
			Class<?> cls=loader.loadClass(CommandTypeEntityAddress);
			
			Method m1 = cls.getDeclaredMethod("getThis");
			
			Object obj = cls.newInstance();	
			
			CE=(Command_cmd)m1.invoke(obj);
			
			
			//�ر�ClassLoader
			ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		//ע������
		regCommandNameList.add(CommandTypeName);
		
		regCommandTypeMap.put(CommandTypeName, CE);
		
	}
	
	*/
	
	
	
	//���ز��
	public void LoadAllPlugins(){
		
		try{
			
			//����
			for(String PluginName:pluginNameList){
				
				//��������ַ���
				String pluginMainClass = pluginMainClassMap.get(PluginName);

				//����ļ�
				File pluginFile = pluginFileMap.get(PluginName);
				
				//�������ʵ��
				ASMCPlugin ASMCP=null;
				
				URL url=pluginFile.toURI().toURL();
				ClassLoader loader=new URLClassLoader(new URL[]{url});//����ClassLoader
								
							
				msg.sendSysMessage("����:"+PluginName);
				
				
				
				Class<?> cls=loader.loadClass(pluginMainClass);
				
				Method m1 = cls.getDeclaredMethod("getThis");
				
				Object obj = cls.newInstance();
				
				
				ASMCP=(ASMCPlugin)m1.invoke(obj);
				
				//���ò������
				ASMCP.setPluginName(PluginName);
				
				//ע����
				installPluginMainClassMap.put(PluginName, ASMCP);
				
				//ע����
				installPluginList.add(PluginName);
//				
//				//��Ӳ���������͵��б�
//				RegCommandTypeList.add(pluginRegCommandTypeName);
				
				
				//�ر�ClassLoader
				ClassLoaderUtil.releaseLoader((URLClassLoader)loader);
			}
			
			
			
			//ִ�в����onEnable
			for(String str:installPluginList){
				
				ASMCPlugin plugin=(ASMCPlugin) installPluginMainClassMap.get(str);			
				plugin.onEnable();
				
			}
			
			//��ʾ��ע�����������
//			for(String str:regCommandNameList){
//				
//				msg.sendSysMessage("ע����������:"+str);
//				
//			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

		
	}
	
	
	
	
	
	
	//���Ҳ��
	public void SearchPlugins(){

		
		msg.sendSysMessage("����ASMC���");
		
		String PluginName = null;
		
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
			
			
	
			//��ȡ
			for(File f:pluginList){
				
				msg.sendSysMessage("��ȡ:"+f.getName());
								
				URL url=new URL("jar:file:/"+f.getPath()+"!/ASMC_Plugin.gd"); 
			
				
				InputStream is=url.openStream(); 		
				String main=v5.getKeyValueOfInputStream(is, "Plugin_Main");
				
				
				is=url.openStream();
				PluginName=v5.getKeyValueOfInputStream(is, "Plugin_Name");
				
				
				//�ж��Ƿ����������
				for(String str:pluginNameList){
					
					if(str.equalsIgnoreCase(PluginName)){
						msg.sendErrorMessage("���ز��:"+PluginName+"ʱ��������,������Ƴ�ͻ!");
						continue;
					}
					
				}
							
				
				//����������
				pluginNameList.add(PluginName);
				
				//�������ļ�
				pluginFileMap.put(PluginName, f);
				
				//����������
				pluginMainClassMap.put(PluginName, main);		
				
			
			}
			
				
			
		}catch(Exception e){		
			e.printStackTrace();			
		}
		

		
		
		
	}



	
	




	public ArrayList<String> getRegCommandTypeList() {
		return regCommandNameList;
	}




	public HashMap<String, Command_cmd> getRegCommandTypeMap() {
		return regCommandTypeMap;
	}




	
	
}
